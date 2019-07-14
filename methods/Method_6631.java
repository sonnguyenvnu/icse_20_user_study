private void applyRemoteLanguage(LocaleInfo localeInfo,String langCode,boolean force,final int currentAccount){
  if (localeInfo == null || localeInfo != null && !localeInfo.isRemote() && !localeInfo.isUnofficial()) {
    return;
  }
  if (localeInfo.hasBaseLang() && (langCode == null || langCode.equals(localeInfo.baseLangCode))) {
    if (localeInfo.baseVersion != 0 && !force) {
      if (localeInfo.hasBaseLang()) {
        TLRPC.TL_langpack_getDifference req=new TLRPC.TL_langpack_getDifference();
        req.from_version=localeInfo.baseVersion;
        req.lang_code=localeInfo.getBaseLangCode();
        req.lang_pack="";
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
          if (response != null) {
            AndroidUtilities.runOnUIThread(() -> saveRemoteLocaleStrings(localeInfo,(TLRPC.TL_langPackDifference)response,currentAccount));
          }
        }
,ConnectionsManager.RequestFlagWithoutLogin);
      }
    }
 else {
      TLRPC.TL_langpack_getLangPack req=new TLRPC.TL_langpack_getLangPack();
      req.lang_code=localeInfo.getBaseLangCode();
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(      TLObject response,      TLRPC.TL_error error) -> {
        if (response != null) {
          AndroidUtilities.runOnUIThread(() -> saveRemoteLocaleStrings(localeInfo,(TLRPC.TL_langPackDifference)response,currentAccount));
        }
      }
,ConnectionsManager.RequestFlagWithoutLogin);
    }
  }
  if (langCode == null || langCode.equals(localeInfo.shortName)) {
    if (localeInfo.version != 0 && !force) {
      TLRPC.TL_langpack_getDifference req=new TLRPC.TL_langpack_getDifference();
      req.from_version=localeInfo.version;
      req.lang_code=localeInfo.getLangCode();
      req.lang_pack="";
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
        if (response != null) {
          AndroidUtilities.runOnUIThread(() -> saveRemoteLocaleStrings(localeInfo,(TLRPC.TL_langPackDifference)response,currentAccount));
        }
      }
,ConnectionsManager.RequestFlagWithoutLogin);
    }
 else {
      for (int a=0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
        ConnectionsManager.setLangCode(localeInfo.getLangCode());
      }
      TLRPC.TL_langpack_getLangPack req=new TLRPC.TL_langpack_getLangPack();
      req.lang_code=localeInfo.getLangCode();
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(      TLObject response,      TLRPC.TL_error error) -> {
        if (response != null) {
          AndroidUtilities.runOnUIThread(() -> saveRemoteLocaleStrings(localeInfo,(TLRPC.TL_langPackDifference)response,currentAccount));
        }
      }
,ConnectionsManager.RequestFlagWithoutLogin);
    }
  }
}
