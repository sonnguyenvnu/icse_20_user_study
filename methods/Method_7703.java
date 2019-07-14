public static ThemeInfo applyThemeFile(File file,String themeName,boolean temporary){
  try {
    if (themeName.equals("Default") || themeName.equals("Dark") || themeName.equals("Blue") || themeName.equals("Dark Blue") || themeName.equals("Graphite") || themeName.equals("Arctic Blue")) {
      return null;
    }
    File finalFile=new File(ApplicationLoader.getFilesDirFixed(),themeName);
    if (!AndroidUtilities.copyFile(file,finalFile)) {
      return null;
    }
    boolean newTheme=false;
    ThemeInfo themeInfo=themesDict.get(themeName);
    if (themeInfo == null) {
      newTheme=true;
      themeInfo=new ThemeInfo();
      themeInfo.name=themeName;
      themeInfo.pathToFile=finalFile.getAbsolutePath();
    }
    if (!temporary) {
      previousTheme=null;
      if (newTheme) {
        themes.add(themeInfo);
        themesDict.put(themeInfo.name,themeInfo);
        otherThemes.add(themeInfo);
        sortThemes();
        saveOtherThemes();
      }
    }
 else {
      previousTheme=currentTheme;
    }
    applyTheme(themeInfo,!temporary,true,false);
    return themeInfo;
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return null;
}
