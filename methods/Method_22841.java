public FontMetrics getFontMetrics(SyntaxStyle style){
  return getFontMetrics(style.isBold() ? boldFont : plainFont);
}
