public void applyLanguage(final LocaleInfo localeInfo,boolean override,boolean init,boolean fromFile,boolean force,final int currentAccount){
  if (localeInfo == null) {
    return;
  }
  boolean hasBase=localeInfo.hasBaseLang();
  File pathToFile=localeInfo.getPathToFile();
  File pathToBaseFile=localeInfo.getPathToBaseFile();
  String shortName=localeInfo.shortName;
  if (!init) {
    ConnectionsManager.setLangCode(localeInfo.getLangCode());
  }
  LocaleInfo existingInfo=getLanguageFromDict(localeInfo.getKey());
  if (existingInfo == null) {
    if (localeInfo.isRemote()) {
      remoteLanguages.add(localeInfo);
      remoteLanguagesDict.put(localeInfo.getKey(),localeInfo);
      languages.add(localeInfo);
      languagesDict.put(localeInfo.getKey(),localeInfo);
      saveOtherLanguages();
    }
 else     if (localeInfo.isUnofficial()) {
      unofficialLanguages.add(localeInfo);
      languagesDict.put(localeInfo.getKey(),localeInfo);
      saveOtherLanguages();
    }
  }
  if ((localeInfo.isRemote() || localeInfo.isUnofficial()) && (force || !pathToFile.exists() || hasBase && !pathToBaseFile.exists())) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("reload locale because one of file doesn't exist" + pathToFile + " " + pathToBaseFile);
    }
    if (init) {
      AndroidUtilities.runOnUIThread(() -> applyRemoteLanguage(localeInfo,null,true,currentAccount));
    }
 else {
      applyRemoteLanguage(localeInfo,null,true,currentAccount);
    }
  }
  try {
    Locale newLocale;
    String[] args;
    if (!TextUtils.isEmpty(localeInfo.pluralLangCode)) {
      args=localeInfo.pluralLangCode.split("_");
    }
 else     if (!TextUtils.isEmpty(localeInfo.baseLangCode)) {
      args=localeInfo.baseLangCode.split("_");
    }
 else {
      args=localeInfo.shortName.split("_");
    }
    if (args.length == 1) {
      newLocale=new Locale(args[0]);
    }
 else {
      newLocale=new Locale(args[0],args[1]);
    }
    if (override) {
      languageOverride=localeInfo.shortName;
      SharedPreferences preferences=MessagesController.getGlobalMainSettings();
      SharedPreferences.Editor editor=preferences.edit();
      editor.putString("language",localeInfo.getKey());
      editor.commit();
    }
    if (pathToFile == null) {
      localeValues.clear();
    }
 else     if (!fromFile) {
      localeValues=getLocaleFileStrings(hasBase ? localeInfo.getPathToBaseFile() : localeInfo.getPathToFile());
      if (hasBase) {
        localeValues.putAll(getLocaleFileStrings(localeInfo.getPathToFile()));
      }
    }
    currentLocale=newLocale;
    currentLocaleInfo=localeInfo;
    if (currentLocaleInfo != null && !TextUtils.isEmpty(currentLocaleInfo.pluralLangCode)) {
      currentPluralRules=allRules.get(currentLocaleInfo.pluralLangCode);
    }
    if (currentPluralRules == null) {
      currentPluralRules=allRules.get(args[0]);
      if (currentPluralRules == null) {
        currentPluralRules=allRules.get(currentLocale.getLanguage());
        if (currentPluralRules == null) {
          currentPluralRules=new PluralRules_None();
        }
      }
    }
    changingConfiguration=true;
    Locale.setDefault(currentLocale);
    android.content.res.Configuration config=new android.content.res.Configuration();
    config.locale=currentLocale;
    ApplicationLoader.applicationContext.getResources().updateConfiguration(config,ApplicationLoader.applicationContext.getResources().getDisplayMetrics());
    changingConfiguration=false;
    if (reloadLastFile) {
      if (init) {
        AndroidUtilities.runOnUIThread(() -> reloadCurrentRemoteLocale(currentAccount,null));
      }
 else {
        reloadCurrentRemoteLocale(currentAccount,null);
      }
      reloadLastFile=false;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
    changingConfiguration=false;
  }
  recreateFormatters();
}
