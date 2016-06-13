/*******************************************************************************
 * FastODS - a Martin Schulz's SimpleODS fork
 *    Copyright (C) 2016 J. Férard <https://github.com/jferard>
 * SimpleODS - A lightweight java library to create simple OpenOffice spreadsheets
 *    Copyright (C) 2008-2013 Martin Schulz <mtschulz at users.sourceforge.net>
 *
 * This file is part of FastODS.
 *
 * FastODS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FastODS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
/*
 * FastODS - a Martin Schulz's SimpleODS fork
 *    Copyright (C) 2016 J. Férard
 * SimpleODS - A lightweight java library to create simple OpenOffice spreadsheets
*    Copyright (C) 2008-2013 Martin Schulz <mtschulz at users.sourceforge.net>
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.github.jferard.fastods.style;

import com.github.jferard.fastods.style.FHTextStyle.Underline;

/**
 * @author Julien Férard
 */
public class FHTextStyleBuilder {
	private String fontColor;
	private String fontName;
	private String fontSize;
	private String fontSizeAsian;
	private String fontSizeComplex;
	private String fontStyle;
	private String fontUnderlineColor;
	private Underline fontUnderlineStyle;
	private String fontWeight;
	private String fontWeightAsian;

	private String fontWeightComplex;
	private final String name;

	/**
	 * Create a new text style without a name.<br>
	 * This is used by class TableFamilyStyle. Version 0.5.2 Added
	 *
	 * @param name2
	 *
	 * @param odsFile
	 *            The file to add this style to
	 */
	public FHTextStyleBuilder(final String name) {
		if (name == null)
			throw new IllegalArgumentException();

		this.name = name;
	}

	public FHTextStyle build() {
		return new FHTextStyle(this.name, this.fontColor, this.fontName,
				this.fontWeight, this.fontStyle, this.fontSize,
				this.fontUnderlineColor, this.fontUnderlineStyle);
	}

	/**
	 * Set the font color to color.
	 *
	 * @param color
	 *            The color to be used in format #rrggbb e.g. #ff0000 for a red
	 *            cell background
	 * @return this for fluent style
	 */
	public FHTextStyleBuilder fontColor(final String color) {
		this.fontColor = color;
		return this;
	}

	/**
	 * Set the font name to be used for this style.
	 *
	 * @param fontName
	 *            The font name for this TextStyle
	 * @return
	 */
	public FHTextStyleBuilder fontName(final String fontName) {
		this.fontName = fontName;
		return this;
	}

	/**
	 * Set the font size in points to the given value.
	 *
	 * @param fontSize
	 *            - The font size as int , e.g. 10 or 8
	 * @return
	 */
	public FHTextStyleBuilder fontSize(final int fontSize) {
		final String size = new StringBuilder(fontSize).append("pt").toString();
		this.fontSize = size;
		return this;
	}

	/**
	 * Set the font size to the given value<br>
	 * fontSize is a length value expressed as a number followed by pt, e.g.
	 * 12pt
	 *
	 * @param fontSize
	 *            - The font size as string, e.g. '10.5pt' or '8pt'
	 * @return
	 */
	public FHTextStyleBuilder fontSize(final String fontSize) {
		this.fontSize = fontSize;
		return this;
	}

	/**
	 * Set the font weight to italic.
	 *
	 * @return true
	 */
	public FHTextStyleBuilder fontStyleItalic() {
		this.fontStyle = "italic";
		return this;
	}

	/**
	 * Set the font weight to italic.
	 *
	 * @return true
	 */
	public FHTextStyleBuilder fontStyleNormal() {
		this.fontStyle = "normal";
		return this;
	}

	/**
	 * Set the font underline color to color. Use an empty string to reset it to
	 * 'auto'.
	 *
	 * @param color
	 *            The color to be used in format #rrggbb e.g. #ff0000 for a red
	 *            cell background.
	 * @return
	 */
	public FHTextStyleBuilder fontUnderlineColor(final String color) {
		this.fontUnderlineColor = color;
		return this;
	}

	/**
	 * Set the style that should be used for the underline. Valid is:<br>
	 * TextStyle.STYLE_UNDERLINE_NONE<br>
	 * TextStyle.STYLE_UNDERLINE_SOLID<br>
	 * TextStyle.STYLE_UNDERLINE_DOTTED<br>
	 * TextStyle.STYLE_UNDERLINE_DASH<br>
	 * TextStyle.STYLE_UNDERLINE_LONGDASH<br>
	 * TextStyle.STYLE_UNDERLINE_DOTDASH<br>
	 * TextStyle.STYLE_UNDERLINE_DOTDOTDASH<br>
	 * TextStyle.STYLE_UNDERLINE_WAVE<br>
	 * Other values are ignored.
	 *
	 * @param style
	 *            One of the TextStyle.STYLE_UNDERLINE
	 * @return
	 */
	public FHTextStyleBuilder fontUnderlineStyle(final Underline style) {
		this.fontUnderlineStyle = style;
		return this;
	}

	/**
	 * Set the font weight to bold.
	 *
	 * @return true
	 */
	public FHTextStyleBuilder fontWeightBold() {
		this.fontWeight = "bold";
		return this;
	}

	/**
	 * Set the font weight to normal.
	 *
	 * @return true -
	 */
	public FHTextStyleBuilder fontWeightNormal() {
		this.fontWeight = "normal";
		return this;
	}
}
