public void loadCurrentState(){
  if (updatingState) {
    return;
  }
  updatingState=true;
  TLRPC.TL_updates_getState req=new TLRPC.TL_updates_getState();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    updatingState=false;
    if (error == null) {
      TLRPC.TL_updates_state res=(TLRPC.TL_updates_state)response;
      MessagesStorage.getInstance(currentAccount).setLastDateValue(res.date);
      MessagesStorage.getInstance(currentAccount).setLastPtsValue(res.pts);
      MessagesStorage.getInstance(currentAccount).setLastSeqValue(res.seq);
      MessagesStorage.getInstance(currentAccount).setLastQtsValue(res.qts);
      for (int a=0; a < 3; a++) {
        processUpdatesQueue(a,2);
      }
      MessagesStorage.getInstance(currentAccount).saveDiffParams(MessagesStorage.getInstance(currentAccount).getLastSeqValue(),MessagesStorage.getInstance(currentAccount).getLastPtsValue(),MessagesStorage.getInstance(currentAccount).getLastDateValue(),MessagesStorage.getInstance(currentAccount).getLastQtsValue());
    }
 else {
      if (error.code != 401) {
        loadCurrentState();
      }
    }
  }
);
}
