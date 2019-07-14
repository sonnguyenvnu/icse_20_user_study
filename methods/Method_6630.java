public void loadRemoteLanguages(final int currentAccount){
  if (loadingRemoteLanguages) {
    return;
  }
  loadingRemoteLanguages=true;
  TLRPC.TL_langpack_getLanguages req=new TLRPC.TL_langpack_getLanguages();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (response != null) {
      AndroidUtilities.runOnUIThread(() -> {
        loadingRemoteLanguages=false;
        TLRPC.Vector res=(TLRPC.Vector)response;
        for (int a=0, size=remoteLanguages.size(); a < size; a++) {
          remoteLanguages.get(a).serverIndex=Integer.MAX_VALUE;
        }
        for (int a=0, size=res.objects.size(); a < size; a++) {
          TLRPC.TL_langPackLanguage language=(TLRPC.TL_langPackLanguage)res.objects.get(a);
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("loaded lang " + language.name);
          }
          LocaleInfo localeInfo=new LocaleInfo();
          localeInfo.nameEnglish=language.name;
          localeInfo.name=language.native_name;
          localeInfo.shortName=language.lang_code.replace('-','_').toLowerCase();
          if (language.base_lang_code != null) {
            localeInfo.baseLangCode=language.base_lang_code.replace('-','_').toLowerCase();
          }
 else {
            localeInfo.baseLangCode="";
          }
          localeInfo.pluralLangCode=language.plural_code.replace('-','_').toLowerCase();
          localeInfo.isRtl=language.rtl;
          localeInfo.pathToFile="remote";
          localeInfo.serverIndex=a;
          LocaleInfo existing=getLanguageFromDict(localeInfo.getKey());
          if (existing == null) {
            languages.add(localeInfo);
            languagesDict.put(localeInfo.getKey(),localeInfo);
          }
 else {
            existing.nameEnglish=localeInfo.nameEnglish;
            existing.name=localeInfo.name;
            existing.baseLangCode=localeInfo.baseLangCode;
            existing.pluralLangCode=localeInfo.pluralLangCode;
            existing.pathToFile=localeInfo.pathToFile;
            existing.serverIndex=localeInfo.serverIndex;
            localeInfo=existing;
          }
          if (!remoteLanguagesDict.containsKey(localeInfo.getKey())) {
            remoteLanguages.add(localeInfo);
            remoteLanguagesDict.put(localeInfo.getKey(),localeInfo);
          }
        }
        for (int a=0; a < remoteLanguages.size(); a++) {
          LocaleInfo info=remoteLanguages.get(a);
          if (info.serverIndex != Integer.MAX_VALUE || info == currentLocaleInfo) {
            continue;
          }
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("remove lang " + info.getKey());
          }
          remoteLanguages.remove(a);
          remoteLanguagesDict.remove(info.getKey());
          languages.remove(info);
          languagesDict.remove(info.getKey());
          a--;
        }
        saveOtherLanguages();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.suggestedLangpack);
        applyLanguage(currentLocaleInfo,true,false,currentAccount);
      }
);
    }
  }
,ConnectionsManager.RequestFlagWithoutLogin);
}
