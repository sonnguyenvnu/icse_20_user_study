@Override public float textDescent(){
  if (textFont == null)   defaultFontOrDeath("textDescent");
  Object font=textFont.getNative();
  float descent=0;
  if (font != null)   descent=pgl.getFontDescent(font);
  if (descent == 0)   descent=super.textDescent();
  return descent;
}
