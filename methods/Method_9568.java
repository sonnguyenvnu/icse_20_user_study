private void loadPasswordSettings(){
  if (UserConfig.getInstance(currentAccount).hasSecureData) {
    return;
  }
  TLRPC.TL_account_getPassword req=new TLRPC.TL_account_getPassword();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (response != null) {
      TLRPC.TL_account_password password=(TLRPC.TL_account_password)response;
      if (password.has_secure_values) {
        AndroidUtilities.runOnUIThread(() -> {
          UserConfig.getInstance(currentAccount).hasSecureData=true;
          UserConfig.getInstance(currentAccount).saveConfig(false);
          updateRows();
        }
);
      }
    }
  }
,ConnectionsManager.RequestFlagFailOnServerErrors | ConnectionsManager.RequestFlagWithoutLogin);
}
