@Override public float textAscent(){
  if (textFont == null)   defaultFontOrDeath("textAscent");
  Object font=textFont.getNative();
  float ascent=0;
  if (font != null)   ascent=pgl.getFontAscent(font);
  if (ascent == 0)   ascent=super.textAscent();
  return ascent;
}
