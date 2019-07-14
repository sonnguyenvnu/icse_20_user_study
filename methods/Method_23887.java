@Override protected float textWidthImpl(char[] buffer,int start,int stop){
  if (textFont == null) {
    defaultFontOrDeath("textWidth");
  }
  if (textFontInfo.font == null) {
    return super.textWidthImpl(buffer,start,stop);
  }
  fontCache.measuringText.setFont(textFontInfo.font);
  fontCache.measuringText.setText(new String(buffer,start,stop - start));
  return (float)fontCache.measuringText.getLayoutBounds().getWidth();
}
