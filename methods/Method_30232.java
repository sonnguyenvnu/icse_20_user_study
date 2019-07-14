@Override public void onStart(){
  super.onStart();
  EventBusUtils.postAsync(new ItemCollectionWriteStartedEvent(mItemCollectionId,this));
}
