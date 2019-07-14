public void saveRemoteLocaleStringsForCurrentLocale(final TLRPC.TL_langPackDifference difference,int currentAccount){
  if (currentLocaleInfo == null) {
    return;
  }
  final String langCode=difference.lang_code.replace('-','_').toLowerCase();
  if (!langCode.equals(currentLocaleInfo.shortName) && !langCode.equals(currentLocaleInfo.baseLangCode)) {
    return;
  }
  saveRemoteLocaleStrings(currentLocaleInfo,difference,currentAccount);
}
