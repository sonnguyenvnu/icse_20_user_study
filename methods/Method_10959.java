@Override public void onCreate(){
  super.onCreate();
  new Thread(new Runnable(){
    @Override public void run(){
      Looper.prepare();
      isSuccess=RxLocationTool.registerLocation(getApplicationContext(),0,0,mOnLocationChangeListener);
      if (isSuccess)       RxToast.success("init success");
      Looper.loop();
    }
  }
).start();
}
