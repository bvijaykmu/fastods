/*
 * FastODS - A very fast and lightweight (no dependency) library for creating ODS
 *    (Open Document Spreadsheet, mainly for Calc) files in Java.
 *    It's a Martin Schulz's SimpleODS fork
 *    Copyright (C) 2016-2018 J. Férard <https://github.com/jferard>
 * SimpleODS - A lightweight java library to create simple OpenOffice spreadsheets
 *    Copyright (C) 2008-2013 Martin Schulz <mtschulz at users.sourceforge.net>
 *
 * This file is part of FastODS.
 *
 * FastODS is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * FastODS is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.jferard.fastods;

import com.github.jferard.fastods.datastyle.DataStyles;
import com.github.jferard.fastods.datastyle.DataStylesBuilder;
import com.github.jferard.fastods.odselement.OdsElements;
import com.github.jferard.fastods.util.EqualityUtil;
import com.github.jferard.fastods.util.FileExists;
import com.github.jferard.fastods.util.FileOpen;
import com.github.jferard.fastods.util.FileOpenResult;
import com.github.jferard.fastods.util.PositionUtil;
import com.github.jferard.fastods.util.TableNameUtil;
import com.github.jferard.fastods.util.WriteUtil;
import com.github.jferard.fastods.util.XMLUtil;
import com.github.jferard.fastods.util.ZipUTF8WriterBuilder;
import com.github.jferard.fastods.util.ZipUTF8WriterImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * An OdsFactory is the entry point for creating ods documents.
 *
 * @author Julien Férard
 */
public class OdsFactory {
    /**
     * @return a default ods factory
     */
    public static OdsFactory create() {
        return OdsFactory.create(Logger.getLogger(NamedOdsDocument.class.getName()), Locale.getDefault());
    }

    /**
     * create an ods factory
     *
     * @param logger the logger
     * @param locale the locale
     * @return the factory
     */
    public static OdsFactory create(final Logger logger, final Locale locale) {
        final PositionUtil positionUtil = new PositionUtil(new EqualityUtil(), new TableNameUtil());
        final WriteUtil writeUtil = WriteUtil.create();
        final XMLUtil xmlUtil = XMLUtil.create();
        final DataStyles format = DataStylesBuilder.create(locale).build();
        return new OdsFactory(logger, positionUtil, writeUtil, xmlUtil, format);
    }

    private final Logger logger;
    private final PositionUtil positionUtil;
    private final WriteUtil writeUtil;
    private final XMLUtil xmlUtil;
    private DataStyles format;


    /**
     * Create a new OdsFactory
     *
     * @param logger       the logger
     * @param positionUtil an util
     * @param writeUtil    an util
     * @param xmlUtil      an util
     * @param format       the data styles
     */
    OdsFactory(final Logger logger, final PositionUtil positionUtil, final WriteUtil writeUtil, final XMLUtil xmlUtil,
               final DataStyles format) {
        this.logger = logger;
        this.positionUtil = positionUtil;
        this.writeUtil = writeUtil;
        this.xmlUtil = xmlUtil;
        this.format = format;
    }

    /**
     * Set the data styles
     *
     * @param ds the data styles
     * @return this for fluent style
     */
    public OdsFactory dataStyles(final DataStyles ds) {
        this.format = ds;
        return this;
    }


    /**
     * Create a new, empty document for an anonymous writer. Use addTable to add tables.
     *
     * @return a new document
     */
    private NamedOdsDocument createNamedDocument() {
        final OdsElements odsElements = OdsElements
                .create(this.positionUtil, this.xmlUtil, this.writeUtil, this.format);
        return new NamedOdsDocument(this.logger, odsElements, this.xmlUtil);
    }

    /**
     * Create a new, empty document for a normal writer. Use addTable to add tables.
     *
     * @return a new document
     */
    private NamedOdsDocument createDocument() {
        final OdsElements odsElements = OdsElements
                .create(this.positionUtil, this.xmlUtil, this.writeUtil, this.format);
        return new NamedOdsDocument(this.logger, odsElements, this.xmlUtil);
    }

    /**
     * @return a new writer, but with no actual name
     */
    public AnonymousOdsFileWriter createWriter() {
        final NamedOdsDocument document = this.createNamedDocument();
        return new AnonymousOdsFileWriter(this.logger, document);
    }

    /**
     * Create a new ODS file writer from a document. Be careful: this method opens immediatly a stream.
     *
     * @param filename the name of the destination file
     * @return the ods writer
     * @throws FileNotFoundException if the file can't be found
     */
    public NamedOdsFileWriter createWriter(final String filename) throws IOException {
        final NamedOdsDocument document = this.createDocument();
        final NamedOdsFileWriter writer = OdsFileDirectWriter.builder(this.logger, document)
                .openResult(this.openFile(filename)).build();
        document.addObserver(writer);
        document.prepareFlush();
        return writer;
    }

    /**
     * Create a new ODS file writer from a document. Be careful: this method opens immediatly a stream.
     *
     * @param file the destination file
     * @return the ods writer
     * @throws IOException if an I/O error occurs
     */
    public NamedOdsFileWriter createWriter(final File file) throws IOException {
        final NamedOdsDocument document = this.createDocument();
        final NamedOdsFileWriter writer = OdsFileDirectWriter.builder(this.logger, document)
                .openResult(this.openFile(file)).build();
        document.addObserver(writer);
        document.prepareFlush();
        return writer;
    }

    /**
     * Create an adapter for a writer.
     *
     * @param file the file
     * @return the adapter
     * @throws IOException if an I/O error occurs
     */
    public OdsFileWriterAdapter createWriterAdapter(final File file) throws IOException {
        final NamedOdsDocument document = this.createDocument();
        final ZipUTF8WriterBuilder zipUTF8Writer = ZipUTF8WriterImpl.builder().noWriterBuffer();
        final OdsFileWriterAdapter writerAdapter = OdsFileWriterAdapter
                .create(OdsFileDirectWriter.builder(this.logger, document).openResult(this.openFile(file))
                        .zipBuilder(zipUTF8Writer).build());
        document.addObserver(writerAdapter);
        document.prepareFlush();
        return writerAdapter;
    }

    /**
     * @param file the file.
     * @return the result of the operation
     * @throws FileNotFoundException if the file does not exist
     */
    public FileOpenResult openFile(final File file) throws FileNotFoundException {
        if (file.isDirectory()) return FileOpenResult.FILE_IS_DIR;

        if (file.exists()) return new FileExists(file);

        return new FileOpen(new FileOutputStream(file));
    }

    /**
     * @param filename the name of the file.
     * @return the result of the operation
     * @throws FileNotFoundException if the file does not exist
     */
    public FileOpenResult openFile(final String filename) throws FileNotFoundException {
        final File f = new File(filename);
        return this.openFile(f);
    }

    /**
     * the file state
     *
     * @deprecated use ??
     */
    @Deprecated
    public enum FileState {
        /**
         * the file is a directory
         */
        IS_DIRECTORY, /**
         * the file already exists
         */
        FILE_EXISTS, /**
         * the file may be written
         */
        OK
    }
}
