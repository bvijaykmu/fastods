/*
 * FastODS - A very fast and lightweight (no dependency) library for creating ODS
 *    (Open Document Spreadsheet, mainly for Calc) files in Java.
 *    It's a Martin Schulz's SimpleODS fork
 *    Copyright (C) 2016-2019 J. Férard <https://github.com/jferard>
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
package com.github.jferard.fastods.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.powermock.api.easymock.PowerMock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;

public class ZipUTF8WriterBuilderTest {
    public static final int C_SIZE = 118;
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private ZipUTF8WriterBuilder builder;
    private ByteArrayOutputStream out;

    @Before
    public void setUp() {
        this.builder = new ZipUTF8WriterBuilder();
        this.out = new ByteArrayOutputStream();
        PowerMock.resetAll();
    }

    @Test
    public final void testDefault() throws IOException {
        final ZipUTF8Writer writer = this.builder.build(this.out);
        writer.putNextEntry(new ZipEntry("a"));
        writer.append('c');
        writer.close();
        Assert.assertEquals(C_SIZE, this.out.size());
    }

    @Test
    public final void testNoZipBuffer() throws IOException {
        final ZipUTF8Writer writer = this.builder.noZipBuffer().build(this.out);
        writer.putNextEntry(new ZipEntry("a"));
        writer.append('c');
        writer.close();
        Assert.assertEquals(ZipUTF8WriterBuilderTest.C_SIZE, this.out.size());
    }

    @Test
    public final void testCustomZipBuffer() throws IOException {
        final ZipUTF8Writer writer = this.builder.zipBuffer(1).build(this.out);
        writer.putNextEntry(new ZipEntry("a"));
        writer.append('c');
        writer.close();
        Assert.assertEquals(ZipUTF8WriterBuilderTest.C_SIZE, this.out.size());
    }

    @Test
    public final void testNoWriterBuffer() throws IOException {
        final ZipUTF8Writer writer = this.builder.noWriterBuffer().build(this.out);
        writer.putNextEntry(new ZipEntry("a"));
        writer.append('c');
        writer.close();
        Assert.assertEquals(ZipUTF8WriterBuilderTest.C_SIZE, this.out.size());
    }

    @Test
    public final void testCustomWriterBuffer() throws IOException {
        final ZipUTF8Writer writer = this.builder.writerBuffer(1).build(this.out);
        writer.putNextEntry(new ZipEntry("a"));
        writer.append('c');
        writer.close();
        Assert.assertEquals(ZipUTF8WriterBuilderTest.C_SIZE, this.out.size());
    }

    @Test
    public final void testExplicitDefault() throws IOException {
        final ZipUTF8Writer writer =
                this.builder.defaultWriterBuffer().defaultZipBuffer().build(this.out);
        writer.putNextEntry(new ZipEntry("a"));
        writer.append('c');
        writer.close();
        Assert.assertEquals(ZipUTF8WriterBuilderTest.C_SIZE, this.out.size());
    }

    @Test
    public final void testBadWriterBufferSize() {
        this.thrown.expect(IllegalArgumentException.class);
        this.builder.writerBuffer(-1).build(this.out);
    }

    @Test
    public final void testBadZipBufferSize() {
        this.thrown.expect(IllegalArgumentException.class);
        this.builder.zipBuffer(-1).build(this.out);
    }

    @Test
    public final void testLevel0() throws IOException {
        final ZipUTF8Writer writer = this.builder.level(0).build(this.out);
        writer.putNextEntry(new ZipEntry("a"));
        writer.append(
                "some long text that can be zipped some long text that can be zipped some long " +
                        "text that can be " + "zipped some long text that can be zipped ");
        writer.close();
        Assert.assertEquals(121, this.out.size());
    }

    @Test
    public final void testLevel9() throws IOException {
        final ZipUTF8Writer writer = this.builder.level(9).build(this.out);
        writer.putNextEntry(new ZipEntry("a"));
        writer.append(
                "some long text that can be zipped some long text that can be zipped some long " +
                        "text that can be zipped some long text that can be zipped ");
        writer.close();
        Assert.assertEquals(ZipUTF8WriterBuilderTest.C_SIZE, this.out.size());
    }

    @Test
    public final void testLevel99() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("invalid compression level");
        this.builder.level(99).build(this.out);
    }
}
