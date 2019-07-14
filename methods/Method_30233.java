@Override public void onResponse(ItemCollection response){
  ToastUtils.show(R.string.item_collection_vote_successful,getContext());
  EventBusUtils.postAsync(new ItemCollectionUpdatedEvent(response,this));
  stopSelf();
}
