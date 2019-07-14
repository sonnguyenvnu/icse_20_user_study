public void unregistedPush(){
  if (UserConfig.getInstance(currentAccount).registeredForPush && SharedConfig.pushString.length() == 0) {
    TLRPC.TL_account_unregisterDevice req=new TLRPC.TL_account_unregisterDevice();
    req.token=SharedConfig.pushString;
    req.token_type=2;
    for (int a=0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
      UserConfig userConfig=UserConfig.getInstance(a);
      if (a != currentAccount && userConfig.isClientActivated()) {
        req.other_uids.add(userConfig.getClientUserId());
      }
    }
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    }
);
  }
}
