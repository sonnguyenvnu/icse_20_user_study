protected void registerObserver(OnDataChangedListener onDataChangedListener){
  this.onDataChangedListener=onDataChangedListener;
  BaseBroadcastReceiver.register(context,receiver,ActionUtil.ACTION_USER_CHANGED);
}
