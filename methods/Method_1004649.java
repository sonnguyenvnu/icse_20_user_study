public void init(ReactContext reactContext){
  mReactContext=reactContext;
  reactContext.addLifecycleEventListener(new LifecycleEventListener(){
    @Override public void onHostResume(){
      Log.d(LOGTAG,"onHostResume");
      switchToVisible();
    }
    @Override public void onHostPause(){
      Log.d(LOGTAG,"onHostPause");
      switchToInvisible();
    }
    @Override public void onHostDestroy(){
      Log.d(LOGTAG,"onHostDestroy");
      switchToInvisible();
    }
  }
);
}
