private void acknowledgeCall(final boolean startRinging){
  if (call instanceof TLRPC.TL_phoneCallDiscarded) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.w("Call " + call.id + " was discarded before the service started, stopping");
    }
    stopSelf();
    return;
  }
  if (Build.VERSION.SDK_INT >= 19 && XiaomiUtilities.isMIUI() && !XiaomiUtilities.isCustomPermissionGranted(XiaomiUtilities.OP_SHOW_WHEN_LOCKED)) {
    if (((KeyguardManager)getSystemService(KEYGUARD_SERVICE)).inKeyguardRestrictedInputMode()) {
      if (BuildVars.LOGS_ENABLED)       FileLog.e("MIUI: no permission to show when locked but the screen is locked. ¯\\_(?)_/¯");
      stopSelf();
      return;
    }
  }
  TLRPC.TL_phone_receivedCall req=new TLRPC.TL_phone_receivedCall();
  req.peer=new TLRPC.TL_inputPhoneCall();
  req.peer.id=call.id;
  req.peer.access_hash=call.access_hash;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,new RequestDelegate(){
    @Override public void run(    final TLObject response,    final TLRPC.TL_error error){
      AndroidUtilities.runOnUIThread(new Runnable(){
        @Override public void run(){
          if (sharedInstance == null)           return;
          if (BuildVars.LOGS_ENABLED) {
            FileLog.w("receivedCall response = " + response);
          }
          if (error != null) {
            if (BuildVars.LOGS_ENABLED) {
              FileLog.e("error on receivedCall: " + error);
            }
            stopSelf();
          }
 else {
            if (USE_CONNECTION_SERVICE) {
              ContactsController.getInstance(currentAccount).createOrUpdateConnectionServiceContact(user.id,user.first_name,user.last_name);
              TelecomManager tm=(TelecomManager)getSystemService(TELECOM_SERVICE);
              Bundle extras=new Bundle();
              extras.putInt("call_type",1);
              tm.addNewIncomingCall(addAccountToTelecomManager(),extras);
            }
            if (startRinging)             startRinging();
          }
        }
      }
);
    }
  }
,ConnectionsManager.RequestFlagFailOnServerErrors);
}
