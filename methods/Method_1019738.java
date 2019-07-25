@Override public void visit(StyleParagraphPropertiesElement ele){
  StyleParagraphProperties paragraphProperties=currentStyle.getParagraphProperties();
  if (paragraphProperties == null) {
    paragraphProperties=new StyleParagraphProperties();
    currentStyle.setParagraphProperties(paragraphProperties);
  }
  String backgroundColor=ele.getFoBackgroundColorAttribute();
  if (StringUtils.isNotEmpty(backgroundColor)) {
    paragraphProperties.setBackgroundColor(ColorRegistry.getInstance().getColor(backgroundColor));
  }
  String border=ele.getFoBorderAttribute();
  if (StringUtils.isNotEmpty(border)) {
    paragraphProperties.setBorder(new StyleBorder(border,BorderType.ALL));
  }
  String borderBottom=ele.getFoBorderBottomAttribute();
  if (StringUtils.isNotEmpty(borderBottom)) {
    paragraphProperties.setBorderBottom(new StyleBorder(borderBottom,BorderType.BOTTOM));
  }
  String borderLeft=ele.getFoBorderLeftAttribute();
  if (StringUtils.isNotEmpty(borderLeft)) {
    paragraphProperties.setBorderLeft(new StyleBorder(borderLeft,BorderType.LEFT));
  }
  String borderRight=ele.getFoBorderRightAttribute();
  if (StringUtils.isNotEmpty(borderRight)) {
    paragraphProperties.setBorderRight(new StyleBorder(borderRight,BorderType.RIGHT));
  }
  String borderTop=ele.getFoBorderTopAttribute();
  if (StringUtils.isNotEmpty(borderTop)) {
    paragraphProperties.setBorderTop(new StyleBorder(borderTop,BorderType.TOP));
  }
  Boolean joinBorder=ele.getStyleJoinBorderAttribute();
  if (joinBorder != null) {
    paragraphProperties.setJoinBorder(joinBorder);
  }
  String lineHeight=ele.getFoLineHeightAttribute();
  if (StringUtils.isNotEmpty(lineHeight)) {
    paragraphProperties.setLineHeight(new StyleLineHeight(ODFUtils.getDimensionAsPoint(lineHeight),ODFUtils.hasPercentUnit(lineHeight)));
  }
  String margin=ele.getFoMarginAttribute();
  if (StringUtils.isNotEmpty(margin)) {
    paragraphProperties.setMargin(ODFUtils.getDimensionAsPoint(margin));
  }
  String marginBottom=ele.getFoMarginBottomAttribute();
  if (StringUtils.isNotEmpty(marginBottom)) {
    paragraphProperties.setMarginBottom(ODFUtils.getDimensionAsPoint(marginBottom));
  }
  String marginLeft=ele.getFoMarginLeftAttribute();
  if (StringUtils.isNotEmpty(marginLeft)) {
    paragraphProperties.setMarginLeft(ODFUtils.getDimensionAsPoint(marginLeft));
  }
  String marginRight=ele.getFoMarginRightAttribute();
  if (StringUtils.isNotEmpty(marginRight)) {
    paragraphProperties.setMarginRight(ODFUtils.getDimensionAsPoint(marginRight));
  }
  String marginTop=ele.getFoMarginTopAttribute();
  if (StringUtils.isNotEmpty(marginTop)) {
    paragraphProperties.setMarginTop(ODFUtils.getDimensionAsPoint(marginTop));
  }
  String padding=ele.getFoPaddingAttribute();
  if (StringUtils.isNotEmpty(padding)) {
    paragraphProperties.setPadding(ODFUtils.getDimensionAsPoint(padding));
  }
  String paddingBottom=ele.getFoPaddingBottomAttribute();
  if (StringUtils.isNotEmpty(paddingBottom)) {
    paragraphProperties.setPaddingBottom(ODFUtils.getDimensionAsPoint(paddingBottom));
  }
  String paddingLeft=ele.getFoPaddingLeftAttribute();
  if (StringUtils.isNotEmpty(paddingLeft)) {
    paragraphProperties.setPaddingLeft(ODFUtils.getDimensionAsPoint(paddingLeft));
  }
  String paddingRight=ele.getFoPaddingRightAttribute();
  if (StringUtils.isNotEmpty(paddingRight)) {
    paragraphProperties.setPaddingRight(ODFUtils.getDimensionAsPoint(paddingRight));
  }
  String paddingTop=ele.getFoPaddingTopAttribute();
  if (StringUtils.isNotEmpty(paddingTop)) {
    paragraphProperties.setPaddingTop(ODFUtils.getDimensionAsPoint(paddingTop));
  }
  String textAlign=ele.getFoTextAlignAttribute();
  if (StringUtils.isNotEmpty(textAlign)) {
    int alignment=Element.ALIGN_UNDEFINED;
    if (FoTextAlignAttribute.Value.START.toString().equals(textAlign)) {
      alignment=Element.ALIGN_LEFT;
    }
 else     if (FoTextAlignAttribute.Value.END.toString().equals(textAlign)) {
      alignment=Element.ALIGN_RIGHT;
    }
 else     if (FoTextAlignAttribute.Value.LEFT.toString().equals(textAlign)) {
      alignment=Element.ALIGN_LEFT;
    }
 else     if (FoTextAlignAttribute.Value.RIGHT.toString().equals(textAlign)) {
      alignment=Element.ALIGN_RIGHT;
    }
 else     if (FoTextAlignAttribute.Value.CENTER.toString().equals(textAlign)) {
      alignment=Element.ALIGN_CENTER;
    }
 else     if (FoTextAlignAttribute.Value.JUSTIFY.toString().equals(textAlign)) {
      alignment=Element.ALIGN_JUSTIFIED;
    }
    paragraphProperties.setAlignment(alignment);
  }
  Boolean autoTextIndent=ele.getStyleAutoTextIndentAttribute();
  if (autoTextIndent != null) {
    paragraphProperties.setAutoTextIndent(autoTextIndent);
  }
  String textIndent=ele.getFoTextIndentAttribute();
  if (StringUtils.isNotEmpty(textIndent)) {
    paragraphProperties.setTextIndent(ODFUtils.getDimensionAsPoint(textIndent));
  }
  String keepTogether=ele.getFoKeepTogetherAttribute();
  if (StringUtils.isNotEmpty(keepTogether)) {
    if (FoKeepTogetherAttribute.Value.ALWAYS.toString().equals(keepTogether)) {
      paragraphProperties.setKeepTogether(Boolean.TRUE);
    }
 else {
      paragraphProperties.setKeepTogether(Boolean.FALSE);
    }
  }
  String breakBefore=ele.getFoBreakBeforeAttribute();
  if (StringUtils.isNotEmpty(breakBefore)) {
    if (FoBreakBeforeAttribute.Value.PAGE.toString().equals(breakBefore)) {
      paragraphProperties.setBreakBefore(StyleBreak.createWithPageBreak());
    }
 else     if (FoBreakBeforeAttribute.Value.COLUMN.toString().equals(breakBefore)) {
      paragraphProperties.setBreakBefore(StyleBreak.createWithColumnBreak());
    }
 else {
      paragraphProperties.setBreakBefore(StyleBreak.createWithNoBreak());
    }
  }
  super.visit(ele);
}
