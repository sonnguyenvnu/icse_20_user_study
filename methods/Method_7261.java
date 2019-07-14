@Override public final void onServiceDisconnected(ComponentName name){
  mService=null;
  onPostMessageServiceDisconnected();
}
