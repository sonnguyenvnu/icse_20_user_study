@Override protected void onListUpdated(List<SimpleUser> userList){
  if (mBroadcast.likeCount < userList.size()) {
    mBroadcast.likeCount=userList.size();
    EventBusUtils.postAsync(new BroadcastUpdatedEvent(mBroadcast,this));
  }
}
