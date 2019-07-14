private void processChannelsUpdatesQueue(int channelId,int state){
  ArrayList<TLRPC.Updates> updatesQueue=updatesQueueChannels.get(channelId);
  if (updatesQueue == null) {
    return;
  }
  int channelPts=channelsPts.get(channelId);
  if (updatesQueue.isEmpty() || channelPts == 0) {
    updatesQueueChannels.remove(channelId);
    return;
  }
  Collections.sort(updatesQueue,(updates,updates2) -> AndroidUtilities.compare(updates.pts,updates2.pts));
  boolean anyProceed=false;
  if (state == 2) {
    channelsPts.put(channelId,updatesQueue.get(0).pts);
  }
  for (int a=0; a < updatesQueue.size(); a++) {
    TLRPC.Updates updates=updatesQueue.get(a);
    int updateState;
    if (updates.pts <= channelPts) {
      updateState=2;
    }
 else     if (channelPts + updates.pts_count == updates.pts) {
      updateState=0;
    }
 else {
      updateState=1;
    }
    if (updateState == 0) {
      processUpdates(updates,true);
      anyProceed=true;
      updatesQueue.remove(a);
      a--;
    }
 else     if (updateState == 1) {
      long updatesStartWaitTime=updatesStartWaitTimeChannels.get(channelId);
      if (updatesStartWaitTime != 0 && (anyProceed || Math.abs(System.currentTimeMillis() - updatesStartWaitTime) <= 1500)) {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("HOLE IN CHANNEL " + channelId + " UPDATES QUEUE - will wait more time");
        }
        if (anyProceed) {
          updatesStartWaitTimeChannels.put(channelId,System.currentTimeMillis());
        }
        return;
      }
 else {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("HOLE IN CHANNEL " + channelId + " UPDATES QUEUE - getChannelDifference ");
        }
        updatesStartWaitTimeChannels.delete(channelId);
        updatesQueueChannels.remove(channelId);
        getChannelDifference(channelId);
        return;
      }
    }
 else {
      updatesQueue.remove(a);
      a--;
    }
  }
  updatesQueueChannels.remove(channelId);
  updatesStartWaitTimeChannels.delete(channelId);
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("UPDATES CHANNEL " + channelId + " QUEUE PROCEED - OK");
  }
}
