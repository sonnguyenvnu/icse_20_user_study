@Override public void onCreate(){
  registerReceiver(receiver1,new IntentFilter(TAG_BROADCAST_EXTRACT_CANCEL));
  context=getApplicationContext();
}
