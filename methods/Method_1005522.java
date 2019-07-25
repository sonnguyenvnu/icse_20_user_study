@Override public void start(OnActivityUpdatedListener listener,@NonNull ActivityParams params){
  this.activityParams=params;
  this.listener=listener;
  IntentFilter intentFilter=new IntentFilter(BROADCAST_INTENT_ACTION);
  context.registerReceiver(activityReceiver,intentFilter);
  if (client.isConnected()) {
    startUpdating(params);
  }
 else   if (stopped) {
    shouldStart=true;
    client.connect();
    stopped=false;
  }
 else {
    shouldStart=true;
    logger.d("still not connected - scheduled start when connection is ok");
  }
}
