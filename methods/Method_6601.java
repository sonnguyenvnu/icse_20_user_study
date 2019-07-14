public boolean applyLanguageFile(File file,int currentAccount){
  try {
    HashMap<String,String> stringMap=getLocaleFileStrings(file);
    String languageName=stringMap.get("LanguageName");
    String languageNameInEnglish=stringMap.get("LanguageNameInEnglish");
    String languageCode=stringMap.get("LanguageCode");
    if (languageName != null && languageName.length() > 0 && languageNameInEnglish != null && languageNameInEnglish.length() > 0 && languageCode != null && languageCode.length() > 0) {
      if (languageName.contains("&") || languageName.contains("|")) {
        return false;
      }
      if (languageNameInEnglish.contains("&") || languageNameInEnglish.contains("|")) {
        return false;
      }
      if (languageCode.contains("&") || languageCode.contains("|") || languageCode.contains("/") || languageCode.contains("\\")) {
        return false;
      }
      File finalFile=new File(ApplicationLoader.getFilesDirFixed(),languageCode + ".xml");
      if (!AndroidUtilities.copyFile(file,finalFile)) {
        return false;
      }
      String key="local_" + languageCode.toLowerCase();
      LocaleInfo localeInfo=getLanguageFromDict(key);
      if (localeInfo == null) {
        localeInfo=new LocaleInfo();
        localeInfo.name=languageName;
        localeInfo.nameEnglish=languageNameInEnglish;
        localeInfo.shortName=languageCode.toLowerCase();
        localeInfo.pluralLangCode=localeInfo.shortName;
        localeInfo.pathToFile=finalFile.getAbsolutePath();
        languages.add(localeInfo);
        languagesDict.put(localeInfo.getKey(),localeInfo);
        otherLanguages.add(localeInfo);
        saveOtherLanguages();
      }
      localeValues=stringMap;
      applyLanguage(localeInfo,true,false,true,false,currentAccount);
      return true;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return false;
}
