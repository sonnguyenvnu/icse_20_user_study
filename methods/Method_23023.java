/** 
 * Get a font from the lib/fonts folder. Our default fonts are also installed there so that the monospace (and others) can be used by other font listing calls (i.e. it appears in the list of monospace fonts in the Preferences window, and can be used by HTMLEditorKit for WebFrame).
 */
static private Font createFont(String filename,int size) throws IOException, FontFormatException {
  boolean registerFont=false;
  File fontFile=new File(System.getProperty("java.home"),"lib/fonts/" + filename);
  if (!fontFile.exists()) {
    fontFile=Platform.getContentFile("lib/fonts/" + filename);
    registerFont=true;
  }
  if (!fontFile.exists()) {
    String msg="Could not find required fonts. ";
    if (Util.containsNonASCII(Platform.getJavaHome().getAbsolutePath())) {
      msg+="Trying moving Processing\n" + "to a location with only ASCII characters in the path.";
    }
 else {
      msg+="Please reinstall Processing.";
    }
    Messages.showError("Font Sadness",msg,null);
  }
  BufferedInputStream input=new BufferedInputStream(new FileInputStream(fontFile));
  Font font=Font.createFont(Font.TRUETYPE_FONT,input);
  input.close();
  if (registerFont) {
    GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
    ge.registerFont(font);
  }
  return font.deriveFont((float)size);
}
