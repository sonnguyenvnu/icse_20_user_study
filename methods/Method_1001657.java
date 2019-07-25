FontConfiguration remove(FontStyle style){
  final EnumSet<FontStyle> r=styles.clone();
  r.remove(style);
  return new FontConfiguration(r,motherFont,motherColor,currentFont,currentColor,extendedColor,fontPosition,svgAttributes,hyperlink,hyperlinkColor,useUnderlineForHyperlink,tabSize);
}
