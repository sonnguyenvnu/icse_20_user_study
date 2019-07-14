private void checkTosUpdate(){
  if (nextTosCheckTime > ConnectionsManager.getInstance(currentAccount).getCurrentTime() || checkingTosUpdate || !UserConfig.getInstance(currentAccount).isClientActivated()) {
    return;
  }
  checkingTosUpdate=true;
  TLRPC.TL_help_getTermsOfServiceUpdate req=new TLRPC.TL_help_getTermsOfServiceUpdate();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    checkingTosUpdate=false;
    if (response instanceof TLRPC.TL_help_termsOfServiceUpdateEmpty) {
      TLRPC.TL_help_termsOfServiceUpdateEmpty res=(TLRPC.TL_help_termsOfServiceUpdateEmpty)response;
      nextTosCheckTime=res.expires;
    }
 else     if (response instanceof TLRPC.TL_help_termsOfServiceUpdate) {
      final TLRPC.TL_help_termsOfServiceUpdate res=(TLRPC.TL_help_termsOfServiceUpdate)response;
      nextTosCheckTime=res.expires;
      AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.needShowAlert,4,res.terms_of_service));
    }
 else {
      nextTosCheckTime=ConnectionsManager.getInstance(currentAccount).getCurrentTime() + 60 * 60;
    }
    notificationsPreferences.edit().putInt("nextTosCheckTime",nextTosCheckTime).commit();
  }
);
}
