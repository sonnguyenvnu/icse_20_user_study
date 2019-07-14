protected String[] getFontFamilies(){
  if (fontFamilyNames == null) {
    GraphicsEnvironment env=GraphicsEnvironment.getLocalGraphicsEnvironment();
    fontFamilyNames=env.getAvailableFontFamilyNames();
  }
  return fontFamilyNames;
}
