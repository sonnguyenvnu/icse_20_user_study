@ReactMethod public void start(final int delay){
  if (!wakeLock.isHeld())   wakeLock.acquire();
  handler=new Handler();
  runnable=new Runnable(){
    @Override public void run(){
      sendEvent(reactContext,"backgroundTimer");
    }
  }
;
  handler.post(runnable);
}
