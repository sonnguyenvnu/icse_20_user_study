public void cascadeFrom(WebvttCssStyle style){
  if (style.hasFontColor) {
    setFontColor(style.fontColor);
  }
  if (style.bold != UNSPECIFIED) {
    bold=style.bold;
  }
  if (style.italic != UNSPECIFIED) {
    italic=style.italic;
  }
  if (style.fontFamily != null) {
    fontFamily=style.fontFamily;
  }
  if (linethrough == UNSPECIFIED) {
    linethrough=style.linethrough;
  }
  if (underline == UNSPECIFIED) {
    underline=style.underline;
  }
  if (textAlign == null) {
    textAlign=style.textAlign;
  }
  if (fontSizeUnit == UNSPECIFIED) {
    fontSizeUnit=style.fontSizeUnit;
    fontSize=style.fontSize;
  }
  if (style.hasBackgroundColor) {
    setBackgroundColor(style.backgroundColor);
  }
}
