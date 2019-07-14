@Override public void onCreate(){
  registerReceiver(receiver1,new IntentFilter(KEY_COMPRESS_BROADCAST_CANCEL));
}
