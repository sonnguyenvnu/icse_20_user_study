private TtmlStyle inherit(TtmlStyle ancestor,boolean chaining){
  if (ancestor != null) {
    if (!hasFontColor && ancestor.hasFontColor) {
      setFontColor(ancestor.fontColor);
    }
    if (bold == UNSPECIFIED) {
      bold=ancestor.bold;
    }
    if (italic == UNSPECIFIED) {
      italic=ancestor.italic;
    }
    if (fontFamily == null) {
      fontFamily=ancestor.fontFamily;
    }
    if (linethrough == UNSPECIFIED) {
      linethrough=ancestor.linethrough;
    }
    if (underline == UNSPECIFIED) {
      underline=ancestor.underline;
    }
    if (textAlign == null) {
      textAlign=ancestor.textAlign;
    }
    if (fontSizeUnit == UNSPECIFIED) {
      fontSizeUnit=ancestor.fontSizeUnit;
      fontSize=ancestor.fontSize;
    }
    if (chaining && !hasBackgroundColor && ancestor.hasBackgroundColor) {
      setBackgroundColor(ancestor.backgroundColor);
    }
  }
  return this;
}
