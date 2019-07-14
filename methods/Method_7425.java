protected void callFailed(int errorCode){
  if (call != null) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("Discarding failed call");
    }
    TLRPC.TL_phone_discardCall req=new TLRPC.TL_phone_discardCall();
    req.peer=new TLRPC.TL_inputPhoneCall();
    req.peer.access_hash=call.access_hash;
    req.peer.id=call.id;
    req.duration=controller != null && controllerStarted ? (int)(controller.getCallDuration() / 1000) : 0;
    req.connection_id=controller != null && controllerStarted ? controller.getPreferredRelayID() : 0;
    req.reason=new TLRPC.TL_phoneCallDiscardReasonDisconnect();
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,new RequestDelegate(){
      @Override public void run(      TLObject response,      TLRPC.TL_error error){
        if (error != null) {
          if (BuildVars.LOGS_ENABLED) {
            FileLog.e("error on phone.discardCall: " + error);
          }
        }
 else {
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("phone.discardCall " + response);
          }
        }
      }
    }
);
  }
  super.callFailed(errorCode);
}
