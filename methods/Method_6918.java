private void processUpdatesQueue(int type,int state){
  ArrayList<TLRPC.Updates> updatesQueue=null;
  if (type == 0) {
    updatesQueue=updatesQueueSeq;
    Collections.sort(updatesQueue,(updates,updates2) -> AndroidUtilities.compare(getUpdateSeq(updates),getUpdateSeq(updates2)));
  }
 else   if (type == 1) {
    updatesQueue=updatesQueuePts;
    Collections.sort(updatesQueue,(updates,updates2) -> AndroidUtilities.compare(updates.pts,updates2.pts));
  }
 else   if (type == 2) {
    updatesQueue=updatesQueueQts;
    Collections.sort(updatesQueue,(updates,updates2) -> AndroidUtilities.compare(updates.pts,updates2.pts));
  }
  if (updatesQueue != null && !updatesQueue.isEmpty()) {
    boolean anyProceed=false;
    if (state == 2) {
      TLRPC.Updates updates=updatesQueue.get(0);
      if (type == 0) {
        MessagesStorage.getInstance(currentAccount).setLastSeqValue(getUpdateSeq(updates));
      }
 else       if (type == 1) {
        MessagesStorage.getInstance(currentAccount).setLastPtsValue(updates.pts);
      }
 else {
        MessagesStorage.getInstance(currentAccount).setLastQtsValue(updates.pts);
      }
    }
    for (int a=0; a < updatesQueue.size(); a++) {
      TLRPC.Updates updates=updatesQueue.get(a);
      int updateState=isValidUpdate(updates,type);
      if (updateState == 0) {
        processUpdates(updates,true);
        anyProceed=true;
        updatesQueue.remove(a);
        a--;
      }
 else       if (updateState == 1) {
        if (getUpdatesStartTime(type) != 0 && (anyProceed || Math.abs(System.currentTimeMillis() - getUpdatesStartTime(type)) <= 1500)) {
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("HOLE IN UPDATES QUEUE - will wait more time");
          }
          if (anyProceed) {
            setUpdatesStartTime(type,System.currentTimeMillis());
          }
          return;
        }
 else {
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("HOLE IN UPDATES QUEUE - getDifference");
          }
          setUpdatesStartTime(type,0);
          updatesQueue.clear();
          getDifference();
          return;
        }
      }
 else {
        updatesQueue.remove(a);
        a--;
      }
    }
    updatesQueue.clear();
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("UPDATES QUEUE PROCEED - OK");
    }
  }
  setUpdatesStartTime(type,0);
}
