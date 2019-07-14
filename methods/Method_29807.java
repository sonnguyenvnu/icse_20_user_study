@Override public void onBroadcastChanged(int requestCode,Broadcast newBroadcast){
  mBroadcast=newBroadcast;
  getListener().onBroadcastChanged(getRequestCode(),newBroadcast);
  ensureCommentListResourceIfHasBroadcast();
}
