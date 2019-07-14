@Override public float textAscent(){
  if (textFont == null) {
    defaultFontOrDeath("textAscent");
  }
  if (textFontInfo.font == null) {
    return super.textAscent();
  }
  return textFontInfo.ascent;
}
