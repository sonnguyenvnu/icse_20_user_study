/** 
 * Attempt to find the native version of this font. (Public so that it can be used by OpenGL or other renderers.)
 */
public Object findNative(){
  if (font == null) {
    if (!fontSearched) {
      font=new Font(name,Font.PLAIN,size);
      if (!font.getPSName().equals(psname)) {
        font=new Font(psname,Font.PLAIN,size);
      }
      if (!font.getPSName().equals(psname)) {
        font=null;
      }
      fontSearched=true;
    }
  }
  return font;
}
