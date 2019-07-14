private void checkContinueText(){
  LocaleController.LocaleInfo englishInfo=null;
  LocaleController.LocaleInfo systemInfo=null;
  LocaleController.LocaleInfo currentLocaleInfo=LocaleController.getInstance().getCurrentLocaleInfo();
  final String systemLang=MessagesController.getInstance(currentAccount).suggestedLangCode;
  String arg=systemLang.contains("-") ? systemLang.split("-")[0] : systemLang;
  String alias=LocaleController.getLocaleAlias(arg);
  for (int a=0; a < LocaleController.getInstance().languages.size(); a++) {
    LocaleController.LocaleInfo info=LocaleController.getInstance().languages.get(a);
    if (info.shortName.equals("en")) {
      englishInfo=info;
    }
    if (info.shortName.replace("_","-").equals(systemLang) || info.shortName.equals(arg) || info.shortName.equals(alias)) {
      systemInfo=info;
    }
    if (englishInfo != null && systemInfo != null) {
      break;
    }
  }
  if (englishInfo == null || systemInfo == null || englishInfo == systemInfo) {
    return;
  }
  TLRPC.TL_langpack_getStrings req=new TLRPC.TL_langpack_getStrings();
  if (systemInfo != currentLocaleInfo) {
    req.lang_code=systemInfo.getLangCode();
    localeInfo=systemInfo;
  }
 else {
    req.lang_code=englishInfo.getLangCode();
    localeInfo=englishInfo;
  }
  req.keys.add("ContinueOnThisLanguage");
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (response != null) {
      TLRPC.Vector vector=(TLRPC.Vector)response;
      if (vector.objects.isEmpty()) {
        return;
      }
      final TLRPC.LangPackString string=(TLRPC.LangPackString)vector.objects.get(0);
      if (string instanceof TLRPC.TL_langPackString) {
        AndroidUtilities.runOnUIThread(() -> {
          if (!destroyed) {
            textView.setText(string.value);
            SharedPreferences preferences=MessagesController.getGlobalMainSettings();
            preferences.edit().putString("language_showed2",systemLang.toLowerCase()).commit();
          }
        }
);
      }
    }
  }
,ConnectionsManager.RequestFlagWithoutLogin);
}
