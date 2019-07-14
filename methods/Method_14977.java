protected void unregisterObserver(){
  onDataChangedListener=null;
  BaseBroadcastReceiver.unregister(context,receiver);
}
