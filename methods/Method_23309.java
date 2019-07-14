/** 
 * Starting with Java 1.5, Apple broke the ability to specify most fonts. This bug was filed years ago as #4769141 at bugreporter.apple.com. More: <a href="http://dev.processing.org/bugs/show_bug.cgi?id=407">Bug 407</a>. <br> This function displays a warning when the font is not found and Java's system font is used. See: <a href="https://github.com/processing/processing/issues/5481">issue #5481</a>
 */
static public Font findFont(String name){
  if (PApplet.platform == PConstants.MACOSX) {
    loadFonts();
    Font maybe=fontDifferent.get(name);
    if (maybe != null) {
      return maybe;
    }
  }
  Font font=new Font(name,Font.PLAIN,1);
  if (systemFontName == null) {
    systemFontName=new Font("",Font.PLAIN,1).getFontName();
  }
  if (!name.equals(systemFontName) && font.getFontName().equals(systemFontName)) {
    PGraphics.showWarning("\"" + name + "\" is not available, " + "so another font will be used. " + "Use PFont.list() to show available fonts.");
  }
  return font;
}
