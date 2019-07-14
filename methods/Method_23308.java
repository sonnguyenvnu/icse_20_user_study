/** 
 * Make an internal list of all installed fonts. This can take a while with a lot of fonts installed, but running it on a separate thread may not help much. As of the commit that's adding this note, loadFonts() will only be called by PFont.list() and when loading a font by name, both of which are occasions when we'd need to block until this was finished anyway. It's also possible that running getAllFonts() on a non-EDT thread could cause graphics system issues. Further, the first fonts are usually loaded at the beginning of a sketch, meaning that sketch startup time will still be affected, even with threading in place. Where we're getting killed on font performance is due to this bug: https://bugs.openjdk.java.net/browse/JDK-8179209
 */
static public void loadFonts(){
  if (fonts == null) {
    GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
    fonts=ge.getAllFonts();
    if (PApplet.platform == PConstants.MACOSX) {
      fontDifferent=new HashMap<>();
      for (      Font font : fonts) {
        fontDifferent.put(font.getName(),font);
      }
    }
  }
}
