protected void endConnectionServiceCall(long delay){
  if (USE_CONNECTION_SERVICE) {
    Runnable r=new Runnable(){
      @Override public void run(){
        if (systemCallConnection != null) {
switch (callDiscardReason) {
case DISCARD_REASON_HANGUP:
            systemCallConnection.setDisconnected(new DisconnectCause(isOutgoing ? DisconnectCause.LOCAL : DisconnectCause.REJECTED));
          break;
case DISCARD_REASON_DISCONNECT:
        systemCallConnection.setDisconnected(new DisconnectCause(DisconnectCause.ERROR));
      break;
case DISCARD_REASON_LINE_BUSY:
    systemCallConnection.setDisconnected(new DisconnectCause(DisconnectCause.BUSY));
  break;
case DISCARD_REASON_MISSED:
systemCallConnection.setDisconnected(new DisconnectCause(isOutgoing ? DisconnectCause.CANCELED : DisconnectCause.MISSED));
break;
default :
systemCallConnection.setDisconnected(new DisconnectCause(DisconnectCause.REMOTE));
break;
}
systemCallConnection.destroy();
systemCallConnection=null;
}
}
}
;
if (delay > 0) AndroidUtilities.runOnUIThread(r,delay);
 else r.run();
}
}
