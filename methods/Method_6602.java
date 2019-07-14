public boolean deleteLanguage(LocaleInfo localeInfo,int currentAccount){
  if (localeInfo.pathToFile == null || localeInfo.isRemote() && localeInfo.serverIndex != Integer.MAX_VALUE) {
    return false;
  }
  if (currentLocaleInfo == localeInfo) {
    LocaleInfo info=null;
    if (systemDefaultLocale.getLanguage() != null) {
      info=getLanguageFromDict(systemDefaultLocale.getLanguage());
    }
    if (info == null) {
      info=getLanguageFromDict(getLocaleString(systemDefaultLocale));
    }
    if (info == null) {
      info=getLanguageFromDict("en");
    }
    applyLanguage(info,true,false,currentAccount);
  }
  unofficialLanguages.remove(localeInfo);
  remoteLanguages.remove(localeInfo);
  remoteLanguagesDict.remove(localeInfo.getKey());
  otherLanguages.remove(localeInfo);
  languages.remove(localeInfo);
  languagesDict.remove(localeInfo.getKey());
  File file=new File(localeInfo.pathToFile);
  file.delete();
  saveOtherLanguages();
  return true;
}
