/** 
 * Update the font family and sizes based on the Preferences window.
 */
protected void updateAppearance(){
  String fontFamily=Preferences.get("editor.font.family");
  int fontSize=Toolkit.zoom(Preferences.getInteger("console.font.size"));
  StyleConstants.setFontFamily(stdStyle,fontFamily);
  StyleConstants.setFontSize(stdStyle,fontSize);
  StyleConstants.setFontFamily(errStyle,fontFamily);
  StyleConstants.setFontSize(errStyle,fontSize);
  clear();
}
