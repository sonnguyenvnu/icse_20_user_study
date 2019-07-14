public static boolean deleteTheme(ThemeInfo themeInfo){
  if (themeInfo.pathToFile == null) {
    return false;
  }
  boolean currentThemeDeleted=false;
  if (currentTheme == themeInfo) {
    applyTheme(defaultTheme,true,false,false);
    currentThemeDeleted=true;
  }
  otherThemes.remove(themeInfo);
  themesDict.remove(themeInfo.name);
  themes.remove(themeInfo);
  File file=new File(themeInfo.pathToFile);
  file.delete();
  saveOtherThemes();
  return currentThemeDeleted;
}
