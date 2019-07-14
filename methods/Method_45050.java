public void changeTheme(String xml){
  InputStream in=getClass().getResourceAsStream(LuytenPreferences.THEME_XML_PATH + xml);
  try {
    if (in != null) {
      setTheme(Theme.load(in));
      for (      OpenFile f : hmap) {
        getTheme().apply(f.textArea);
      }
    }
  }
 catch (  Exception e1) {
    Luyten.showExceptionDialog("Exception!",e1);
  }
}
