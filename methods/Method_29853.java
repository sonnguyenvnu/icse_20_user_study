@Override public void onResponse(Broadcast response){
  ToastUtils.show(mLike ? R.string.broadcast_like_successful : R.string.broadcast_unlike_successful,getContext());
  EventBusUtils.postAsync(new BroadcastUpdatedEvent(response,this));
  stopSelf();
}
