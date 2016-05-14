/*
*	SimpleODS - A lightweight java library to create simple OpenOffice spreadsheets
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
package org.simpleods;

/**
 * @author Martin Schulz<br>
 * 
 * Copyright 2008-2013 Martin Schulz <mtschulz at users.sourceforge.net><br>
 * 
 * This file PageStyle.java is part of SimpleODS.
 *
 */
public class PageStyle {
	
	public final static int STYLE_PAPERFORMAT_A3 = 0;
	public final static int STYLE_PAPERFORMAT_A4 = 1;
	public final static int STYLE_PAPERFORMAT_A5 = 2;
	public final static int STYLE_PAPERFORMAT_LETTER = 3;
	public final static int STYLE_PAPERFORMAT_LEGAL = 4;
	public final static int STYLE_PAPERFORMAT_USER = 255;	// Change nPaperFormat to 255 if setPageWidth() or setPageHeigth() is used
	
	public final static int STYLE_PRINTORIENTATION_VERTICAL = 0;
	public final static int STYLE_PRINTORIENTATION_HORIZONTAL = 1;
	
	public final static int STYLE_WRITINGMODE_LRTB	= 0;	// lr-tb (left to right; top to bottom)
	public final static int STYLE_WRITINGMODE_RLTB	= 1;
	public final static int STYLE_WRITINGMODE_TBRL = 2;
	public final static int STYLE_WRITINGMODE_TBLR = 3;
	public final static int STYLE_WRITINGMODE_LR	= 4;
	public final static int STYLE_WRITINGMODE_RL	= 5;
	public final static int STYLE_WRITINGMODE_TB	= 6;
	public final static int STYLE_WRITINGMODE_PAGE	= 7;
	
	private String sName;
	private String sMarginTop="1.5cm";
	private String sMarginBottom="1.5cm";
	private String sMarginLeft="1.5cm";
	private String sMarginRight="1.5cm";
	
	private String sPageWidth = "29.7cm";
	private String sPageHeight = "21.0cm";
	private String sNumFormat ="1";
	private String sBackgroundColor = "";
	
	private String sTextStyleFooter = "";
	private String sTextStyleHeader = "";
	private String sTextHeader = "";
	private String sTextFooter = "";
	
	private int nPrintOrientation = PageStyle.STYLE_PRINTORIENTATION_VERTICAL;
	private int nPaperFormat = PageStyle.STYLE_PAPERFORMAT_A4;
	private int nWritingMode = PageStyle.STYLE_WRITINGMODE_LRTB;

	/**
	 * The OdsFile where this object belong to
	 */
	private OdsFile o;
	
	/**
	 * Create a new page style.
	 * Version 0.5.0 Added parameter OdsFile o
	 * @param sName		A unique name for this style
	 */
	public PageStyle(final String sName,OdsFile odsFile) {
		this.sName = sName;
		o = odsFile;
		o.getStyles().addPageStyle(this);
	}

	public void setPrintOrientationVertical() {
		this.nPrintOrientation = STYLE_PRINTORIENTATION_VERTICAL;
	}

	public void setPrintOrientationHorizontal() {
		this.nPrintOrientation = STYLE_PRINTORIENTATION_HORIZONTAL;
	}
	
	/**
	 * Get the paper format as one of PageStyle.STYLE_PAPERFORMAT_*.
	 */
	public int getPaperFormat() {
		return this.nPaperFormat;
	}
	
	/**
	 * Set the paper format to one of<br>
	 * PageStyle.STYLE_PAPERFORMAT_A3<br>
	 * PageStyle.STYLE_PAPERFORMAT_A4<br>
	 * PageStyle.STYLE_PAPERFORMAT_A5<br>
	 * PageStyle.STYLE_PAPERFORMAT_LETTER<br>
	 * PageStyle.STYLE_PAPERFORMAT_LEGAL<br>
	 * PageStyle.STYLE_PAPERFORMAT_USER	, automatically used if you use setPageHeight() or setPageWidth().
	 * @param nPaperFormat
	 */
	public void setPaperFormat(final int nPaperFormat) {

		this.nPaperFormat = nPaperFormat;
		switch (nPaperFormat) {
		case STYLE_PAPERFORMAT_A3:
			sPageWidth = "42.0cm";
			sPageHeight = "29.7cm";
			break;
		case STYLE_PAPERFORMAT_A4:
			sPageWidth = "29.7cm";
			sPageHeight = "21.0cm";
			break;
		case STYLE_PAPERFORMAT_A5:
			sPageWidth = "21.0cm";
			sPageHeight = "14.8cm";
			break;
		case STYLE_PAPERFORMAT_LETTER:
			sPageWidth = "27.94cm";
			sPageHeight = "21.59cm";
			break;
		case STYLE_PAPERFORMAT_LEGAL:
			sPageWidth = "35.57cm";
			sPageHeight = "21.59cm";
			break;
		default:
			sPageWidth = "29.7cm";
			sPageHeight = "21.0cm";
			this.nPaperFormat = PageStyle.STYLE_PAPERFORMAT_A4;
		}
	}
	
	/**
	 * Get the writing mode<br>.
	 * STYLE_WRITINGMODE_LRTB	lr-tb (left to right; top to bottom)<br>
	 * STYLE_WRITINGMODE_RLTB<br>
	 * STYLE_WRITINGMODE_TBRL<br>
	 * STYLE_WRITINGMODE_TBLR<br>
	 * STYLE_WRITINGMODE_LR<br>
	 * STYLE_WRITINGMODE_RL<br>
	 * STYLE_WRITINGMODE_TB<br>
	 * STYLE_WRITINGMODE_PAGE<br>
	 * @return The current writing mode.
	 */
	public int getWritingMode() {
		return nWritingMode;
	}

	/**
	 * Set the writing mode to one of<br>
	 * STYLE_WRITINGMODE_LRTB	lr-tb (left to right; top to bottom)<br>
	 * STYLE_WRITINGMODE_RLTB<br>
	 * STYLE_WRITINGMODE_TBRL<br>
	 * STYLE_WRITINGMODE_TBLR<br>
	 * STYLE_WRITINGMODE_LR<br>
	 * STYLE_WRITINGMODE_RL<br>
	 * STYLE_WRITINGMODE_TB<br>
	 * STYLE_WRITINGMODE_PAGE<br>
	 * @param writingMode
	 */
	public void setWritingMode(final int writingMode) {
		nWritingMode = writingMode;
	}

	public String getPageHeight() {
		return sPageHeight;
	}

	/**
	 * Set the page height.
	 * pageHeight is a length value expressed as a number followed by a unit of measurement
	 * e.g. 1.5cm or 12px<br>
	 * The valid units in OpenDocument are in, cm, mm, px (pixels), pc (picas; 6 picas equals one inch), 
	 * and pt (points; 72points equal one inch).<br>
	 * When using this method, the paper format is set to PageStyle.STYLE_PAPERFORMAT_USER
	 * @param pageHeight
	 */
	public void setPageHeight(final String pageHeight) {
		this.nPaperFormat = PageStyle.STYLE_PAPERFORMAT_USER;
		sPageHeight = pageHeight;
	}

	public String getPageWidth() {
		return sPageWidth;
	}
	
	/**
	 * Set the page width.
	 * pageWidth is a length value expressed as a number followed by a unit of measurement
	 * e.g. 1.5cm or 12px<br>
	 * The valid units in OpenDocument are in, cm, mm, px (pixels), pc (picas; 6 picas equals one inch), 
	 * and pt (points; 72points equal one inch).<br>
	 * When using this method, the paper format is set to PageStyle.STYLE_PAPERFORMAT_USER
	 * @param pageWidth
	 */
	public void setPageWidth(final String pageWidth) {
		this.nPaperFormat = PageStyle.STYLE_PAPERFORMAT_USER;
		sPageWidth = pageWidth;
	}

	public String getMarginBottom() {
		return sMarginBottom;
	}

	/**
	 * Set the margin at the bottom.
	 * margin is a length value expressed as a number followed by a unit of measurement
	 * e.g. 1.5cm or 12px<br>
	 * The valid units in OpenDocument are in, cm, mm, px (pixels), pc (picas; 6 picas equals one inch), 
	 * and pt (points; 72points equal one inch).<br>
	 * @param margin
	 */
	public void setMarginBottom(final String margin) {
		sMarginBottom = margin;
	}

	public String getMarginLeft() {
		return sMarginLeft;
	}

	/**
	 * Set the margin at the left.
	 * margin is a length value expressed as a number followed by a unit of measurement
	 * e.g. 1.5cm or 12px<br>
	 * The valid units in OpenDocument are in, cm, mm, px (pixels), pc (picas; 6 picas equals one inch), 
	 * and pt (points; 72points equal one inch).<br>
	 * @param margin
	 */
	public void setMarginLeft(final String margin) {
		sMarginLeft = margin;
	}


	public String getMarginRight() {
		return sMarginRight;
	}

	/**
	 * Set the margin at the right.
	 * margin is a length value expressed as a number followed by a unit of measurement e.g. 1.5cm or 12px<br>
	 * The valid units in OpenDocument are in, cm, mm, px (pixels), pc (picas; 6 picas equals one inch),<br> 
	 * and pt (points; 72points equal one inch).<br>
	 * @param margin
	 */
	public void setMarginRight(final String margin) {
		sMarginRight = margin;
	}

	public String getMarginTop() {
		return sMarginTop;
	}

	/**
	 * Set the margin at the top.
	 * margin is a length value expressed as a number followed by a unit of measurement e.g. 1.5cm or 12px<br>
	 * The valid units in OpenDocument are in, cm, mm, px (pixels), pc (picas; 6 picas equals one inch),<br> 
	 * and pt (points; 72points equal one inch).<br>
	 * @param margin
	 */
	public void setMarginTop(final String margin) {
		sMarginTop = margin;
	}
	
	/**
	 * Set the margin at the top,bottom,left and right.
	 * margin is a length value expressed as a number followed by a unit of measurement e.g. 1.5cm or 12px<br>
	 * The valid units in OpenDocument are in, cm, mm, px (pixels), pc (picas; 6 picas equals one inch),<br> 
	 * and pt (points; 72points equal one inch).<br>
	 * @param margin
	 */
	public void setAllMargins(final String margin) {
		setMarginTop(margin);
		setMarginBottom(margin);
		setMarginLeft(margin);
		setMarginRight(margin);
	}
	
	public String getBackgroundColor() {
		return sBackgroundColor;
	}

	/**
	 * Set the background color to sColor, a six-digit hex value. Example: #aa32f0.<br> 
	 * The background color may also be set to 'transparent' if a background image is used (currently unsupported).
	 * 
	 * @param sColor
	 */
	public void setBackgroundColor(final String sColor) {
		this.sBackgroundColor = sColor;
	}

	/**
	 * Get the name of this page style.
	 * @return The page style name
	 */
	public String getName() {
		return this.sName;
	}
	
	/**
	 * Return the master-style informations for this PageStyle.
	 * 
	 * @return The master style representation for styles.xml
	 */
	protected String toMasterStyleXML() {
		StringBuffer sbTemp = new StringBuffer();

		sbTemp.append("<style:master-page style:name=\"DefaultMasterPage\" ");
		sbTemp.append("style:page-layout-name=\"" + this.getName() + "\">");

		sbTemp.append("<style:header>");
		if (o.getStyles().getHeader() == null) {
			sbTemp.append("<text:p text:style-name=\"" + this.sTextStyleHeader + "\">" + this.sTextHeader + "</text:p>");
		} else {
			sbTemp.append(o.getStyles().getHeader().toMasterStyleXML());
		}
		sbTemp.append("</style:header>");

		sbTemp.append("<style:footer>");
		if (o.getStyles().getFooter() == null) {
			sbTemp.append("<text:p text:style-name=\"" + this.sTextStyleFooter + "\">" + this.sTextFooter + "</text:p>");
		} else {
			sbTemp.append(o.getStyles().getFooter().toMasterStyleXML());
		}
		sbTemp.append("</style:footer>");

		sbTemp.append("</style:master-page>");

		return sbTemp.toString();
	}
		
	/**
	 * Write the XML format for this object.<br>
	 * This is used while writing the ODS file.
	 * 
	 * @return The XML string for this object.
	 */
	protected String toXML() {
		StringBuffer sbTemp = new StringBuffer();

		sbTemp.append("<style:page-layout style:name=\"" + this.getName() + "\">");
		sbTemp.append("<style:page-layout-properties ");
		sbTemp.append("fo:page-width=\"" + this.getPageWidth() + "\" "); 
		sbTemp.append("fo:page-height=\"" + this.getPageHeight() + "\" ");
		sbTemp.append("style:num-format=\"" + this.sNumFormat + "\" ");
		sbTemp.append(addWritingMode());
		sbTemp.append(addPrintOrientation());
		sbTemp.append(addBackgroundColor());
		sbTemp.append("fo:margin-top=\"" + this.getMarginTop() + "\" ");
		sbTemp.append("fo:margin-bottom=\"" + this.getMarginBottom() + "\" ");
		sbTemp.append("fo:margin-left=\"" + this.getMarginLeft() + "\" ");
		sbTemp.append("fo:margin-right=\"" + this.getMarginRight() + "\" ");
		sbTemp.append("/>");	// End of page-layout-properties

		sbTemp.append("<style:header-style />");
		
		addHeaderStyle(sbTemp);
		addFooterStyle(sbTemp);
		/*
		if( styles.getFooter()==null ) {
			sbTemp.append("<style:footer-style />");
		} else {
			Footer f = styles.getFooter();
			sbTemp.append("<style:footer-style>");
			sbTemp.append("<style:header-footer-properties ");
			sbTemp.append("fo:min-height=\""+f.getMinHeight()+"\" ");
			sbTemp.append("fo:margin-left=\""+f.getMarginLeft()+"\" ");
			sbTemp.append("fo:margin-right=\""+f.getMarginRight()+"\" ");
			sbTemp.append("fo:margin-top=\""+f.getsMarginTop()+"\"/>");
			sbTemp.append("</style:footer-style>");		
		}*/
		sbTemp.append("</style:page-layout>");

		return sbTemp.toString();
	}

	private String addBackgroundColor() {
		if (this.getBackgroundColor().length() == 0) {
			return "";
		}

		return "fo:background-color=\"" + this.getBackgroundColor() + "\" ";

	}
	
	private String addPrintOrientation() {
		StringBuffer sbTemp = new StringBuffer();
		if (this.nPrintOrientation == PageStyle.STYLE_PRINTORIENTATION_VERTICAL) {
			sbTemp.append("style:print-orientation=\"portrait\" ");
		} else {
			sbTemp.append("style:print-orientation=\"landscape\" ");
		}
		return sbTemp.toString();
	}

	private String addWritingMode() {
		StringBuffer sbTemp = new StringBuffer();

		sbTemp.append("style:writing-mode=\"");

		switch (this.getWritingMode()) {
		case STYLE_WRITINGMODE_LRTB: // lr-tb (left to right; top to bottom)
			sbTemp.append("lr-tb");
			break;
		case STYLE_WRITINGMODE_RLTB:
			sbTemp.append("rl-tb");
			break;
		case STYLE_WRITINGMODE_TBRL:
			sbTemp.append("tb-rl");
			break;
		case STYLE_WRITINGMODE_TBLR:
			sbTemp.append("tb_lr");
			break;
		case STYLE_WRITINGMODE_LR:
			sbTemp.append("lr");
			break;
		case STYLE_WRITINGMODE_RL:
			sbTemp.append("rl");
			break;
		case STYLE_WRITINGMODE_TB:
			sbTemp.append("tb");
			break;
		case STYLE_WRITINGMODE_PAGE:
			sbTemp.append("page");
			break;
		default:
			sbTemp.append("lr-tb");
		}

		sbTemp.append("\" ");
		return sbTemp.toString();
	}
		
	protected void addHeaderStyle(final StringBuffer sbTemp) {

		if (o.getStyles().getHeader() == null) {
			sbTemp.append("<style:header-style />");
		} else {
			Header h = o.getStyles().getHeader();
			sbTemp.append("<style:header-style>");
			sbTemp.append("<style:header-footer-properties ");
			sbTemp.append("fo:min-height=\"" + h.getMinHeight() + "\" ");
			sbTemp.append("fo:margin-left=\"" + h.getMarginLeft() + "\" ");
			sbTemp.append("fo:margin-right=\"" + h.getMarginRight() + "\" ");
			sbTemp.append("fo:margin-top=\"" + h.getMarginTop() + "\"/>");
			sbTemp.append("</style:header-style>");
		}

	}
	

	protected void addFooterStyle(final StringBuffer sbTemp) {

		if (o.getStyles().getFooter() == null) {
			sbTemp.append("<style:footer-style />");
		} else {
			Footer f = o.getStyles().getFooter();
			sbTemp.append("<style:footer-style>");
			sbTemp.append("<style:header-footer-properties ");
			sbTemp.append("fo:min-height=\"" + f.getMinHeight() + "\" ");
			sbTemp.append("fo:margin-left=\"" + f.getMarginLeft() + "\" ");
			sbTemp.append("fo:margin-right=\"" + f.getMarginRight() + "\" ");
			sbTemp.append("fo:margin-top=\"" + f.getMarginTop() + "\"/>");
			sbTemp.append("</style:footer-style>");
		}

	}

}
