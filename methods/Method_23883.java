@Override public float textDescent(){
  if (textFont == null) {
    defaultFontOrDeath("textDescent");
  }
  if (textFontInfo.font == null) {
    return super.textDescent();
  }
  return textFontInfo.descent;
}
