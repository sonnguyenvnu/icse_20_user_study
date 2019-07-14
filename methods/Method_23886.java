/** 
 * FX specific. When setting font or size, new font has to be created. Both textFontImpl and textSizeImpl call this one.
 * @param which font to be set, not null
 * @param size size to be set, greater than zero
 */
protected void handleTextFont(PFont which,float size){
  textFont=which;
  String fontName=which.getName();
  String fontPsName=which.getPostScriptName();
  textFontInfo=fontCache.get(fontName,size);
  if (textFontInfo == null) {
    Font font=null;
    if (which.isStream()) {
      String filename=fontCache.nameToFilename.get(fontName);
      font=Font.loadFont(parent.createInput(filename),size);
    }
    if (font == null) {
      font=new Font(fontName,size);
      if (!fontName.equalsIgnoreCase(font.getName())) {
        font=new Font(fontPsName,size);
        if (!fontPsName.equalsIgnoreCase(font.getName())) {
          font=null;
        }
      }
    }
    if (font == null && which.getNative() != null) {
      font=new Font(size);
    }
    textFontInfo=fontCache.createFontInfo(font);
    fontCache.put(fontName,size,textFontInfo);
  }
  context.setFont(textFontInfo.font);
}
