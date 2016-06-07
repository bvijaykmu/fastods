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

import java.util.Locale;

/**
 * @author Julien Férard Copyright (C) 2016 J. Férard Copyright 2008-2013 Martin
 *         Schulz <mtschulz at users.sourceforge.net>
 *
 *         This file CurrencyStyleBuilder.java is part of FastODS.
 *
 */
public class FloatStyleBuilder extends NumberStyleBuilder<FloatStyle, FloatStyleBuilder>{
	protected int decimalPlaces;

	/**
	 * The builder
	 * 
	 * @param name
	 *            - The name of this style
	 * @param locale
	 */
	protected FloatStyleBuilder(final String name, final Locale locale) {
		super(name, locale);
		this.decimalPlaces = 2;
		this.minIntegerDigits = 1;
	}

	/**
	 * Set how many digits are to the right of the decimal symbol.
	 *
	 * @param decimalPlaces
	 *            - The number of digits
	 */
	public FloatStyleBuilder decimalPlaces(final int decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
		return this;
	}

	@Override
	public FloatStyle build() {
		return new FloatStyle(this.name, this.languageCode, this.countryCode,
				this.volatileStyle, this.decimalPlaces, this.grouping,
				this.minIntegerDigits, this.negativeValueColor);
	}
}