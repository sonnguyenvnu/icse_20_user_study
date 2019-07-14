/** 
 * Check whether the specified font can be used with the PDF library.
 * @param name name of the font
 * @return true if it's ok
 */
protected void checkFont(){
  Font awtFont=(Font)textFont.getNative();
  if (awtFont == null) {
    throw new RuntimeException("Use createFont() instead of loadFont() " + "when drawing text using the PDF library.");
  }
 else   if (textMode != SHAPE) {
    if (textFont.isStream()) {
      throw new RuntimeException("Use textMode(SHAPE) with PDF when loading " + ".ttf and .otf files with createFont().");
    }
 else     if (mapper.getAliases().get(textFont.getName()) == null) {
      if (textFont.getName().equals("Lucida Sans")) {
        throw new RuntimeException("Use textMode(SHAPE) with the default " + "font when exporting to PDF.");
      }
 else {
        throw new RuntimeException("Use textMode(SHAPE) with " + "“" + textFont.getName() + "” " + "when exporting to PDF.");
      }
    }
  }
}
