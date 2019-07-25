FontConfiguration add(FontStyle style){
  final EnumSet<FontStyle> r=styles.clone();
  if (style == FontStyle.PLAIN) {
    r.clear();
  }
  r.add(style);
  return new FontConfiguration(r,motherFont,motherColor,currentFont,currentColor,extendedColor,fontPosition,svgAttributes,hyperlink,hyperlinkColor,useUnderlineForHyperlink,tabSize);
}
