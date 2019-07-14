public void reloadCurrentRemoteLocale(int currentAccount,String langCode){
  if (langCode != null) {
    langCode=langCode.replace("-","_");
  }
  if (langCode == null || currentLocaleInfo != null && (langCode.equals(currentLocaleInfo.shortName) || langCode.equals(currentLocaleInfo.baseLangCode))) {
    applyRemoteLanguage(currentLocaleInfo,langCode,true,currentAccount);
  }
}
