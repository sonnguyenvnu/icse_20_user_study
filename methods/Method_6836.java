protected void processNewChannelDifferenceParams(int pts,int pts_count,int channelId){
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("processNewChannelDifferenceParams pts = " + pts + " pts_count = " + pts_count + " channeldId = " + channelId);
  }
  int channelPts=channelsPts.get(channelId);
  if (channelPts == 0) {
    channelPts=MessagesStorage.getInstance(currentAccount).getChannelPtsSync(channelId);
    if (channelPts == 0) {
      channelPts=1;
    }
    channelsPts.put(channelId,channelPts);
  }
  if (channelPts + pts_count == pts) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("APPLY CHANNEL PTS");
    }
    channelsPts.put(channelId,pts);
    MessagesStorage.getInstance(currentAccount).saveChannelPts(channelId,pts);
  }
 else   if (channelPts != pts) {
    long updatesStartWaitTime=updatesStartWaitTimeChannels.get(channelId);
    boolean gettingDifferenceChannel=gettingDifferenceChannels.get(channelId);
    if (gettingDifferenceChannel || updatesStartWaitTime == 0 || Math.abs(System.currentTimeMillis() - updatesStartWaitTime) <= 1500) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("ADD CHANNEL UPDATE TO QUEUE pts = " + pts + " pts_count = " + pts_count);
      }
      if (updatesStartWaitTime == 0) {
        updatesStartWaitTimeChannels.put(channelId,System.currentTimeMillis());
      }
      UserActionUpdatesPts updates=new UserActionUpdatesPts();
      updates.pts=pts;
      updates.pts_count=pts_count;
      updates.chat_id=channelId;
      ArrayList<TLRPC.Updates> arrayList=updatesQueueChannels.get(channelId);
      if (arrayList == null) {
        arrayList=new ArrayList<>();
        updatesQueueChannels.put(channelId,arrayList);
      }
      arrayList.add(updates);
    }
 else {
      getChannelDifference(channelId);
    }
  }
}
