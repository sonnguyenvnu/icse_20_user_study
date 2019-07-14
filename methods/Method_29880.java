private void onSuccessSimple(Broadcast broadcast){
  ToastUtils.show(R.string.broadcast_send_successful,getContext());
  EventBusUtils.postAsync(new BroadcastSentEvent(mId,broadcast,this));
  stopSelf();
}
