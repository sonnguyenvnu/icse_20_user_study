@Override public void visit(StyleTextPropertiesElement ele){
  StyleTextProperties textProperties=currentStyle.getTextProperties();
  if (textProperties == null) {
    textProperties=new StyleTextProperties(options.getFontProvider());
    currentStyle.setTextProperties(textProperties);
  }
  textProperties.setFontEncoding(options.getFontEncoding());
  String backgroundColor=ele.getFoBackgroundColorAttribute();
  if (StringUtils.isNotEmpty(backgroundColor)) {
    textProperties.setBackgroundColor(ColorRegistry.getInstance().getColor(backgroundColor));
  }
  String color=ele.getFoColorAttribute();
  if (StringUtils.isNotEmpty(color)) {
    textProperties.setFontColor(ColorRegistry.getInstance().getColor(color));
  }
  String fontFamily=ele.getFoFontFamilyAttribute();
  if (StringUtils.isNotEmpty(fontFamily)) {
    textProperties.setFontName(ODFUtils.stripTrailingDigits(fontFamily));
  }
  String fontFamilyAsian=ele.getStyleFontFamilyAsianAttribute();
  if (StringUtils.isNotEmpty(fontFamilyAsian)) {
    textProperties.setFontNameAsian(ODFUtils.stripTrailingDigits(fontFamilyAsian));
  }
  String fontFamilyComplex=ele.getStyleFontFamilyComplexAttribute();
  if (StringUtils.isNotEmpty(fontFamilyComplex)) {
    textProperties.setFontNameComplex(ODFUtils.stripTrailingDigits(fontFamilyComplex));
  }
  String fontName=ele.getStyleFontNameAttribute();
  if (StringUtils.isNotEmpty(fontName)) {
    textProperties.setFontName(ODFUtils.stripTrailingDigits(fontName));
  }
  String fontNameAsian=ele.getStyleFontNameAsianAttribute();
  if (StringUtils.isNotEmpty(fontNameAsian)) {
    textProperties.setFontNameAsian(ODFUtils.stripTrailingDigits(fontNameAsian));
  }
  String fontNameComplex=ele.getStyleFontNameComplexAttribute();
  if (StringUtils.isNotEmpty(fontNameComplex)) {
    textProperties.setFontNameComplex(ODFUtils.stripTrailingDigits(fontNameComplex));
  }
  String fontSize=ele.getFoFontSizeAttribute();
  if (StringUtils.isNotEmpty(fontSize)) {
    if (ODFUtils.hasPercentUnit(fontSize)) {
      if (textProperties.getFontSize() != Font.UNDEFINED) {
        textProperties.setFontSize(textProperties.getFontSize() * ODFUtils.getDimensionAsPoint(fontSize));
      }
    }
 else {
      textProperties.setFontSize(ODFUtils.getDimensionAsPoint(fontSize));
    }
  }
  String fontSizeAsian=ele.getStyleFontSizeAsianAttribute();
  if (StringUtils.isNotEmpty(fontSizeAsian)) {
    if (ODFUtils.hasPercentUnit(fontSizeAsian)) {
      if (textProperties.getFontSizeAsian() != Font.UNDEFINED) {
        textProperties.setFontSizeAsian(textProperties.getFontSizeAsian() * ODFUtils.getDimensionAsPoint(fontSizeAsian));
      }
    }
 else {
      textProperties.setFontSizeAsian(ODFUtils.getDimensionAsPoint(fontSizeAsian));
    }
  }
  String fontSizeComplex=ele.getStyleFontSizeComplexAttribute();
  if (StringUtils.isNotEmpty(fontSizeComplex)) {
    if (ODFUtils.hasPercentUnit(fontSizeComplex)) {
      if (textProperties.getFontSizeComplex() != Font.UNDEFINED) {
        textProperties.setFontSizeComplex(textProperties.getFontSizeComplex() * ODFUtils.getDimensionAsPoint(fontSizeComplex));
      }
    }
 else {
      textProperties.setFontSizeComplex(ODFUtils.getDimensionAsPoint(fontSizeComplex));
    }
  }
  String fontStyle=ele.getFoFontStyleAttribute();
  if (StringUtils.isNotEmpty(fontStyle)) {
    if (FoFontStyleAttribute.Value.NORMAL.toString().equals(fontStyle)) {
      textProperties.setFontItalic(Boolean.FALSE);
    }
 else {
      textProperties.setFontItalic(Boolean.TRUE);
    }
  }
  String fontStyleAsian=ele.getStyleFontStyleAsianAttribute();
  if (StringUtils.isNotEmpty(fontStyleAsian)) {
    if (StyleFontStyleAsianAttribute.Value.NORMAL.toString().equals(fontStyleAsian)) {
      textProperties.setFontItalicAsian(Boolean.FALSE);
    }
 else {
      textProperties.setFontItalicAsian(Boolean.TRUE);
    }
  }
  String fontStyleComplex=ele.getStyleFontStyleComplexAttribute();
  if (StringUtils.isNotEmpty(fontStyleComplex)) {
    if (StyleFontStyleComplexAttribute.Value.NORMAL.toString().equals(fontStyleComplex)) {
      textProperties.setFontItalicComplex(Boolean.FALSE);
    }
 else {
      textProperties.setFontItalicComplex(Boolean.TRUE);
    }
  }
  String fontVariant=ele.getFoFontVariantAttribute();
  if (StringUtils.isNotEmpty(fontVariant)) {
  }
  String fontWeight=ele.getFoFontWeightAttribute();
  if (StringUtils.isNotEmpty(fontWeight)) {
    if (FoFontWeightAttribute.Value.NORMAL.toString().equals(fontWeight)) {
      textProperties.setFontBold(Boolean.FALSE);
    }
 else {
      textProperties.setFontBold(Boolean.TRUE);
    }
  }
  String fontWeightAsian=ele.getStyleFontWeightAsianAttribute();
  if (StringUtils.isNotEmpty(fontWeightAsian)) {
    if (StyleFontWeightAsianAttribute.Value.NORMAL.toString().equals(fontWeightAsian)) {
      textProperties.setFontBoldAsian(Boolean.FALSE);
    }
 else {
      textProperties.setFontBoldAsian(Boolean.TRUE);
    }
  }
  String fontWeightComplex=ele.getStyleFontWeightComplexAttribute();
  if (StringUtils.isNotEmpty(fontWeightComplex)) {
    if (StyleFontWeightComplexAttribute.Value.NORMAL.toString().equals(fontWeightComplex)) {
      textProperties.setFontBoldComplex(Boolean.FALSE);
    }
 else {
      textProperties.setFontBoldComplex(Boolean.TRUE);
    }
  }
  String underlineStyle=ele.getStyleTextUnderlineStyleAttribute();
  if (StringUtils.isNotEmpty(underlineStyle)) {
    if (StyleTextUnderlineStyleAttribute.Value.NONE.toString().equals(underlineStyle)) {
      textProperties.setFontUnderline(Boolean.FALSE);
    }
 else {
      textProperties.setFontUnderline(Boolean.TRUE);
    }
  }
  String underlineType=ele.getStyleTextUnderlineTypeAttribute();
  if (StringUtils.isNotEmpty(underlineType)) {
  }
  String lineThroughStyle=ele.getStyleTextLineThroughStyleAttribute();
  if (StringUtils.isNotEmpty(lineThroughStyle)) {
    if (StyleTextLineThroughStyleAttribute.Value.NONE.toString().equals(lineThroughStyle)) {
      textProperties.setFontStrikeThru(Boolean.FALSE);
    }
 else {
      textProperties.setFontStrikeThru(Boolean.TRUE);
    }
  }
  String textPositionStyle=ele.getStyleTextPositionAttribute();
  if (StringUtils.isNotEmpty(textPositionStyle)) {
    if (textPositionStyle.contains("super")) {
      textProperties.setTextPosition(5.0f);
    }
 else     if (textPositionStyle.contains("sub")) {
      textProperties.setTextPosition(-2.0f);
    }
  }
  super.visit(ele);
}
