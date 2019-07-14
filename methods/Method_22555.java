/** 
 * Read the saved language 
 */
static private String loadLanguage(){
  try {
    if (prefFile.exists()) {
      String language=PApplet.loadStrings(prefFile)[0];
      language=language.trim().toLowerCase();
      if (language.trim().length() != 0) {
        return language;
      }
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return null;
}
