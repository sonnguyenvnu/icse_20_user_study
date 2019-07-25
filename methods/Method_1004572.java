@Override public void detach(){
  if (mBroadcastReceiver != null) {
    mRegisterReceiver.unregisterReceiver(mBroadcastReceiver);
    mBroadcastReceiver=null;
  }
  super.detach();
}
