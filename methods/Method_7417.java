public void declineIncomingCall(int reason,final Runnable onDone){
  stopRinging();
  callDiscardReason=reason;
  if (currentState == STATE_REQUESTING) {
    if (delayedStartOutgoingCall != null) {
      AndroidUtilities.cancelRunOnUIThread(delayedStartOutgoingCall);
      callEnded();
    }
 else {
      dispatchStateChanged(STATE_HANGING_UP);
      endCallAfterRequest=true;
      AndroidUtilities.runOnUIThread(new Runnable(){
        @Override public void run(){
          if (currentState == STATE_HANGING_UP) {
            callEnded();
          }
        }
      }
,5000);
    }
    return;
  }
  if (currentState == STATE_HANGING_UP || currentState == STATE_ENDED)   return;
  dispatchStateChanged(STATE_HANGING_UP);
  if (call == null) {
    if (onDone != null)     onDone.run();
    callEnded();
    if (callReqId != 0) {
      ConnectionsManager.getInstance(currentAccount).cancelRequest(callReqId,false);
      callReqId=0;
    }
    return;
  }
  TLRPC.TL_phone_discardCall req=new TLRPC.TL_phone_discardCall();
  req.peer=new TLRPC.TL_inputPhoneCall();
  req.peer.access_hash=call.access_hash;
  req.peer.id=call.id;
  req.duration=controller != null && controllerStarted ? (int)(controller.getCallDuration() / 1000) : 0;
  req.connection_id=controller != null && controllerStarted ? controller.getPreferredRelayID() : 0;
switch (reason) {
case DISCARD_REASON_DISCONNECT:
    req.reason=new TLRPC.TL_phoneCallDiscardReasonDisconnect();
  break;
case DISCARD_REASON_MISSED:
req.reason=new TLRPC.TL_phoneCallDiscardReasonMissed();
break;
case DISCARD_REASON_LINE_BUSY:
req.reason=new TLRPC.TL_phoneCallDiscardReasonBusy();
break;
case DISCARD_REASON_HANGUP:
default :
req.reason=new TLRPC.TL_phoneCallDiscardReasonHangup();
break;
}
final boolean wasNotConnected=ConnectionsManager.getInstance(currentAccount).getConnectionState() != ConnectionsManager.ConnectionStateConnected;
final Runnable stopper;
if (wasNotConnected) {
if (onDone != null) onDone.run();
callEnded();
stopper=null;
}
 else {
stopper=new Runnable(){
@Override public void run(){
if (done) return;
done=true;
if (onDone != null) onDone.run();
callEnded();
}
}
;
AndroidUtilities.runOnUIThread(stopper,(int)(VoIPServerConfig.getDouble("hangup_ui_timeout",5) * 1000));
}
ConnectionsManager.getInstance(currentAccount).sendRequest(req,new RequestDelegate(){
@Override public void run(TLObject response,TLRPC.TL_error error){
if (error != null) {
if (BuildVars.LOGS_ENABLED) {
FileLog.e("error on phone.discardCall: " + error);
}
}
 else {
if (response instanceof TLRPC.TL_updates) {
TLRPC.TL_updates updates=(TLRPC.TL_updates)response;
MessagesController.getInstance(currentAccount).processUpdates(updates,false);
}
if (BuildVars.LOGS_ENABLED) {
FileLog.d("phone.discardCall " + response);
}
}
if (!wasNotConnected) {
AndroidUtilities.cancelRunOnUIThread(stopper);
if (onDone != null) onDone.run();
}
}
}
,ConnectionsManager.RequestFlagFailOnServerErrors);
}
