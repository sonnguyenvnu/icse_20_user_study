public void onDeviceConfigurationChange(Configuration newConfig){
  if (changingConfiguration) {
    return;
  }
  is24HourFormat=DateFormat.is24HourFormat(ApplicationLoader.applicationContext);
  systemDefaultLocale=newConfig.locale;
  if (languageOverride != null) {
    LocaleInfo toSet=currentLocaleInfo;
    currentLocaleInfo=null;
    applyLanguage(toSet,false,false,UserConfig.selectedAccount);
  }
 else {
    Locale newLocale=newConfig.locale;
    if (newLocale != null) {
      String d1=newLocale.getDisplayName();
      String d2=currentLocale.getDisplayName();
      if (d1 != null && d2 != null && !d1.equals(d2)) {
        recreateFormatters();
      }
      currentLocale=newLocale;
      if (currentLocaleInfo != null && !TextUtils.isEmpty(currentLocaleInfo.pluralLangCode)) {
        currentPluralRules=allRules.get(currentLocaleInfo.pluralLangCode);
      }
      if (currentPluralRules == null) {
        currentPluralRules=allRules.get(currentLocale.getLanguage());
        if (currentPluralRules == null) {
          currentPluralRules=allRules.get("en");
        }
      }
    }
  }
  String newSystemLocale=getSystemLocaleStringIso639();
  if (currentSystemLocale != null && !newSystemLocale.equals(currentSystemLocale)) {
    currentSystemLocale=newSystemLocale;
    ConnectionsManager.setSystemLangCode(currentSystemLocale);
  }
}
