/** 
 * Save the language directly to a settings file. This is 'save' and not 'set' because a language change requires a restart of Processing.
 */
static public void saveLanguage(String language){
  try {
    Util.saveFile(language,prefFile);
    prefFile.setWritable(true,false);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  Platform.saveLanguage(language);
}
