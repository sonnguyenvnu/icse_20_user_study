public void checkUpdateForCurrentRemoteLocale(int currentAccount,int version,int baseVersion){
  if (currentLocaleInfo == null || currentLocaleInfo != null && !currentLocaleInfo.isRemote() && !currentLocaleInfo.isUnofficial()) {
    return;
  }
  if (currentLocaleInfo.hasBaseLang()) {
    if (currentLocaleInfo.baseVersion < baseVersion) {
      applyRemoteLanguage(currentLocaleInfo,currentLocaleInfo.baseLangCode,false,currentAccount);
    }
  }
  if (currentLocaleInfo.version < version) {
    applyRemoteLanguage(currentLocaleInfo,currentLocaleInfo.shortName,false,currentAccount);
  }
}
