@Override public void initialize(){
  ReactContext context=getReactApplicationContext();
  LocalBroadcastManager manager=LocalBroadcastManager.getInstance(context);
  eventHandler=new MusicEvents(context);
  manager.registerReceiver(eventHandler,new IntentFilter(Utils.EVENT_INTENT));
}
