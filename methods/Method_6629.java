public void saveRemoteLocaleStrings(LocaleInfo localeInfo,final TLRPC.TL_langPackDifference difference,int currentAccount){
  if (difference == null || difference.strings.isEmpty() || localeInfo == null || localeInfo.isLocal()) {
    return;
  }
  final String langCode=difference.lang_code.replace('-','_').toLowerCase();
  int type;
  if (langCode.equals(localeInfo.shortName)) {
    type=0;
  }
 else   if (langCode.equals(localeInfo.baseLangCode)) {
    type=1;
  }
 else {
    type=-1;
  }
  if (type == -1) {
    return;
  }
  File finalFile;
  if (type == 0) {
    finalFile=localeInfo.getPathToFile();
  }
 else {
    finalFile=localeInfo.getPathToBaseFile();
  }
  try {
    final HashMap<String,String> values;
    if (difference.from_version == 0) {
      values=new HashMap<>();
    }
 else {
      values=getLocaleFileStrings(finalFile,true);
    }
    for (int a=0; a < difference.strings.size(); a++) {
      TLRPC.LangPackString string=difference.strings.get(a);
      if (string instanceof TLRPC.TL_langPackString) {
        values.put(string.key,escapeString(string.value));
      }
 else       if (string instanceof TLRPC.TL_langPackStringPluralized) {
        values.put(string.key + "_zero",string.zero_value != null ? escapeString(string.zero_value) : "");
        values.put(string.key + "_one",string.one_value != null ? escapeString(string.one_value) : "");
        values.put(string.key + "_two",string.two_value != null ? escapeString(string.two_value) : "");
        values.put(string.key + "_few",string.few_value != null ? escapeString(string.few_value) : "");
        values.put(string.key + "_many",string.many_value != null ? escapeString(string.many_value) : "");
        values.put(string.key + "_other",string.other_value != null ? escapeString(string.other_value) : "");
      }
 else       if (string instanceof TLRPC.TL_langPackStringDeleted) {
        values.remove(string.key);
      }
    }
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("save locale file to " + finalFile);
    }
    BufferedWriter writer=new BufferedWriter(new FileWriter(finalFile));
    writer.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
    writer.write("<resources>\n");
    for (    HashMap.Entry<String,String> entry : values.entrySet()) {
      writer.write(String.format("<string name=\"%1$s\">%2$s</string>\n",entry.getKey(),entry.getValue()));
    }
    writer.write("</resources>");
    writer.close();
    boolean hasBase=localeInfo.hasBaseLang();
    final HashMap<String,String> valuesToSet=getLocaleFileStrings(hasBase ? localeInfo.getPathToBaseFile() : localeInfo.getPathToFile());
    if (hasBase) {
      valuesToSet.putAll(getLocaleFileStrings(localeInfo.getPathToFile()));
    }
    AndroidUtilities.runOnUIThread(() -> {
      if (localeInfo != null) {
        if (type == 0) {
          localeInfo.version=difference.version;
        }
 else {
          localeInfo.baseVersion=difference.version;
        }
      }
      saveOtherLanguages();
      try {
        if (currentLocaleInfo == localeInfo) {
          Locale newLocale;
          String[] args;
          if (!TextUtils.isEmpty(localeInfo.pluralLangCode)) {
            args=localeInfo.pluralLangCode.split("_");
          }
 else           if (!TextUtils.isEmpty(localeInfo.baseLangCode)) {
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
          if (newLocale != null) {
            languageOverride=localeInfo.shortName;
            SharedPreferences preferences=MessagesController.getGlobalMainSettings();
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("language",localeInfo.getKey());
            editor.commit();
          }
          if (newLocale != null) {
            localeValues=valuesToSet;
            currentLocale=newLocale;
            currentLocaleInfo=localeInfo;
            if (currentLocaleInfo != null && !TextUtils.isEmpty(currentLocaleInfo.pluralLangCode)) {
              currentPluralRules=allRules.get(currentLocaleInfo.pluralLangCode);
            }
            if (currentPluralRules == null) {
              currentPluralRules=allRules.get(currentLocale.getLanguage());
              if (currentPluralRules == null) {
                currentPluralRules=allRules.get("en");
              }
            }
            changingConfiguration=true;
            Locale.setDefault(currentLocale);
            Configuration config=new Configuration();
            config.locale=currentLocale;
            ApplicationLoader.applicationContext.getResources().updateConfiguration(config,ApplicationLoader.applicationContext.getResources().getDisplayMetrics());
            changingConfiguration=false;
          }
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
        changingConfiguration=false;
      }
      recreateFormatters();
      NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.reloadInterface);
    }
);
  }
 catch (  Exception ignore) {
  }
}
