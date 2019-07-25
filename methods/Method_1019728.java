public void merge(StyleTextProperties textProperties){
  if (textProperties.getBackgroundColor() != null) {
    backgroundColor=textProperties.getBackgroundColor();
  }
  if (textProperties.getFontBold() != null) {
    fontBold=textProperties.getFontBold();
  }
  if (textProperties.getFontBoldAsian() != null) {
    fontBoldAsian=textProperties.getFontBoldAsian();
  }
  if (textProperties.getFontBoldComplex() != null) {
    fontBoldComplex=textProperties.getFontBoldComplex();
  }
  if (textProperties.getFontColor() != null) {
    fontColor=textProperties.getFontColor();
  }
  if (textProperties.getFontEncoding() != null) {
    fontEncoding=textProperties.getFontEncoding();
  }
  if (textProperties.getFontItalic() != null) {
    fontItalic=textProperties.getFontItalic();
  }
  if (textProperties.getFontItalicAsian() != null) {
    fontItalicAsian=textProperties.getFontItalicAsian();
  }
  if (textProperties.getFontItalicComplex() != null) {
    fontItalicComplex=textProperties.getFontItalicComplex();
  }
  if (textProperties.getFontName() != null) {
    fontName=textProperties.getFontName();
  }
  if (textProperties.getFontNameAsian() != null) {
    fontNameAsian=textProperties.getFontNameAsian();
  }
  if (textProperties.getFontNameComplex() != null) {
    fontNameComplex=textProperties.getFontNameComplex();
  }
  if (textProperties.getFontSize() != Font.UNDEFINED) {
    fontSize=textProperties.getFontSize();
  }
  if (textProperties.getFontSizeAsian() != Font.UNDEFINED) {
    fontSizeAsian=textProperties.getFontSizeAsian();
  }
  if (textProperties.getFontSizeComplex() != Font.UNDEFINED) {
    fontSizeComplex=textProperties.getFontSizeComplex();
  }
  if (textProperties.getFontStrikeThru() != null) {
    fontStrikeThru=textProperties.getFontStrikeThru();
  }
  if (textProperties.getFontUnderline() != null) {
    fontUnderline=textProperties.getFontUnderline();
  }
  if (textProperties.getTextPosition() != null) {
    textPosition=textProperties.getTextPosition();
  }
}
