/*
 * FastODS - a Martin Schulz's SimpleODS fork
 *    Copyright (C) 2016-2017 J. Férard <https://github.com/jferard>
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

package com.github.jferard.fastods.odselement;

import com.github.jferard.fastods.Table;
import com.github.jferard.fastods.odselement.config.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3.10 office:settings
 * <p>
 * A typical {@code settings.xml} file has two {@code config-item-set}s:
 * <ul>
 * <li>{@code ooo:view-settings}
 * <ul>
 * <li>{@code config-item}s for the view settings</li>
 * <li>{@code config-item-map-indexed} with a {@code config-item-map-entry} per view
 * <ul>
 * <li>{@code config-item}s of the wiew</li>
 * <li>a {@code config-item-map-named} with a {@code config-item-map-entry} per table
 * <ul>
 * <li>{@code config-item}s of the table in the wiew</li>
 * </ul>
 * </li>
 * </ul>
 * </li>
 * </ul>
 * <li>{@code ooo:configuration-settings}
 * <ul>
 * <li>{@code config-item}s for the configuration settings</li>
 * </ul>
 * </li>
 * </ul>
 * *
 *
 * @author Julien Férard
 * @author Martin Schulz
 */
@SuppressWarnings("PMD.CommentRequired")
public class Settings {
    public static Settings create() {
        final ConfigItemSet viewSettings = new ConfigItemSet("ooo:view-settings");

        viewSettings.add(new ConfigItem("VisibleAreaTop", "int", "0"));
        viewSettings.add(new ConfigItem("VisibleAreaLeft", "int", "0"));
        viewSettings.add(new ConfigItem("VisibleAreaWidth", "int", "680"));
        viewSettings.add(new ConfigItem("VisibleAreaHeight", "int", "400"));

        final ConfigItemMapEntrySet firstView = ConfigItemMapEntrySet.createSet();
        firstView.add(new ConfigItem("ViewId", "string", "View1"));
        firstView.add(new ConfigItem("ActiveTable", "string", "Tab1"));
        firstView.add(new ConfigItem("HorizontalScrollbarWidth", "int", "270"));
        firstView.add(new ConfigItem("ZoomType", "short", "0"));
        firstView.add(new ConfigItem("ZoomValue", "int", "100"));
        firstView.add(new ConfigItem("PageViewZoomValue", "int", "60"));
        firstView.add(new ConfigItem("ShowPageBreakPreview", "boolean", "false"));
        firstView.add(new ConfigItem("ShowZeroValues", "boolean", "true"));
        firstView.add(new ConfigItem("ShowNotes", "boolean", "true"));
        firstView.add(new ConfigItem("ShowGrid", "boolean", "true"));
        firstView.add(new ConfigItem("GridColor", "long", "12632256"));
        firstView.add(new ConfigItem("ShowPageBreaks", "boolean", "true"));
        firstView.add(new ConfigItem("HasColumnRowHeaders", "boolean", "true"));
        firstView.add(new ConfigItem("HasSheetTabs", "boolean", "true"));
        firstView.add(new ConfigItem("IsOutlineSymbolsSet", "boolean", "true"));
        firstView.add(new ConfigItem("IsSnapToRaster", "boolean", "false"));
        firstView.add(new ConfigItem("RasterIsVisible", "boolean", "false"));
        firstView.add(new ConfigItem("RasterResolutionX", "int", "1000"));
        firstView.add(new ConfigItem("RasterResolutionY", "int", "1000"));
        firstView.add(new ConfigItem("RasterSubdivisionX", "int", "1"));
        firstView.add(new ConfigItem("RasterSubdivisionY", "int", "1"));
        firstView.add(new ConfigItem("IsRasterAxisSynchronized", "boolean", "true"));

        final ConfigItemSet configurationSettings = new ConfigItemSet("ooo:configuration-settings");
        configurationSettings.add(new ConfigItem("ShowZeroValues", "boolean", "true"));
        configurationSettings.add(new ConfigItem("ShowNotes", "boolean", "true"));
        configurationSettings.add(new ConfigItem("ShowGrid", "boolean", "true"));
        configurationSettings.add(new ConfigItem("GridColor", "long", "12632256"));
        configurationSettings.add(new ConfigItem("ShowPageBreaks", "boolean", "true"));
        configurationSettings.add(new ConfigItem("LinkUpdateMode", "short", "3"));
        configurationSettings.add(new ConfigItem("HasColumnRowHeaders", "boolean", "true"));
        configurationSettings.add(new ConfigItem("HasSheetTabs", "boolean", "true"));
        configurationSettings.add(new ConfigItem("IsOutlineSymbolsSet", "boolean", "true"));
        configurationSettings.add(new ConfigItem("IsSnapToRaster", "boolean", "false"));
        configurationSettings.add(new ConfigItem("RasterIsVisible", "boolean", "false"));
        configurationSettings.add(new ConfigItem("RasterResolutionX", "int", "1000"));
        configurationSettings.add(new ConfigItem("RasterResolutionY", "int", "1000"));
        configurationSettings.add(new ConfigItem("RasterSubdivisionX", "int", "1"));
        configurationSettings.add(new ConfigItem("RasterSubdivisionY", "int", "1"));
        configurationSettings.add(new ConfigItem("IsRasterAxisSynchronized", "boolean", "true"));
        configurationSettings.add(new ConfigItem("AutoCalculate", "boolean", "true"));
        configurationSettings.add(new ConfigItem("PrinterName", "string", ""));
        configurationSettings.add(new ConfigItem("PrinterSetup", "base64Binary", ""));
        configurationSettings.add(new ConfigItem("ApplyUserData", "boolean", "true"));
        configurationSettings.add(new ConfigItem("CharacterCompressionType", "short", "0"));
        configurationSettings.add(new ConfigItem("IsKernAsianPunctuation", "boolean", "false"));
        configurationSettings.add(new ConfigItem("SaveVersionOnClose", "boolean", "false"));
        configurationSettings.add(new ConfigItem("UpdateFromTemplate", "boolean", "true"));
        configurationSettings.add(new ConfigItem("AllowPrintJobCancel", "boolean", "true"));
        configurationSettings.add(new ConfigItem("LoadReadonly", "boolean", "false"));

        return Settings.create(viewSettings, firstView, configurationSettings);
    }

    static Settings create(final ConfigItemSet viewSettings, final ConfigItemMapEntrySet firstView,
                                 final ConfigItemSet configurationSettings) {
        final List<ConfigBlock> rootBlocks = new ArrayList<ConfigBlock>();
        final ConfigItemMapIndexed views = new ConfigItemMapIndexed("Views");
        final Map<String, ConfigItemMapEntrySet> viewById = new HashMap<String, ConfigItemMapEntrySet>();
        final ConfigItemMapNamed tablesMap = new ConfigItemMapNamed("Tables");

        return new Settings(rootBlocks, viewSettings, views, viewById, firstView, tablesMap, configurationSettings);
    }

    private final ConfigItemMapEntrySet firstView;
    private final List<ConfigBlock> rootBlocks;
    private final ConfigItemMapNamed tablesMap;
    private final Map<String, ConfigItemMapEntrySet> viewById;

    Settings(final List<ConfigBlock> rootBlocks, final ConfigItemSet viewSettings, final ConfigItemMapIndexed views,
             final Map<String, ConfigItemMapEntrySet> viewById, final ConfigItemMapEntrySet firstView,
             final ConfigItemMapNamed tablesMap,
             final ConfigItemSet configurationSettings) {
        this.rootBlocks = rootBlocks;
        this.viewById = viewById;
        this.firstView = firstView;
        this.tablesMap = tablesMap;

        // build tree
        views.add(this.firstView);
        viewSettings.add(views);
        this.rootBlocks.add(viewSettings);
        this.viewById.put(((ConfigItem) firstView.getByName("ViewId")).getValue(), firstView);
        this.firstView.add(this.tablesMap);
        this.rootBlocks.add(configurationSettings);
    }

    public void addTable(final Table table) {
        final ConfigItemMapEntry configEntry = table.getConfigEntry();
        this.addTableConfig(configEntry);
    }

    public void addTableConfig(final ConfigItemMapEntry configEntry) {
        this.tablesMap.put(configEntry);
    }

    public List<ConfigBlock> getRootBlocks() {
        return this.rootBlocks;
    }

    /**
     * Set the active table , this is the table that is shown if you open the
     * file.
     *
     * @param table The table to show
     */
    public void setActiveTable(final Table table) {
        this.firstView.add(new ConfigItem("ActiveTable", "string",
                table.getName()));
    }

    public void setTables(final List<Table> tables) {
        this.tablesMap.clear();
        for (final Table table : tables)
            this.addTableConfig(table.getConfigEntry());
    }

    public void setViewSetting(final String viewId, final String item, final String value) {
        final ConfigItemMapEntrySet view = this.viewById.get(viewId);
        if (view == null)
            return;

        view.set(item, value);
    }
}