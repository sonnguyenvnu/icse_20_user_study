@Override protected void handleTextSize(float size){
  Object font=textFont.getNative();
  if (font != null) {
    Object dfont=pgl.getDerivedFont(font,size);
    textFont.setNative(dfont);
  }
  super.handleTextSize(size);
}
