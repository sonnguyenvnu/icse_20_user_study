@Override protected void onListUpdated(List<RebroadcastItem> rebroadcastList){
  if (mBroadcast.rebroadcastCount < rebroadcastList.size()) {
    mBroadcast.rebroadcastCount=rebroadcastList.size();
    EventBusUtils.postAsync(new BroadcastUpdatedEvent(mBroadcast,this));
  }
}
