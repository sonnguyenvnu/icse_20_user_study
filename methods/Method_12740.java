public void invokeApplication() throws Exception {
  final Exception[] temp=new Exception[1];
  RunUtil.runOnUiThread(new Runnable(){
    @Override public void run(){
      if (mApplication != null) {
        return;
      }
      try {
        mApplication=makeApplication(false,mPluginManager.getInstrumentation());
      }
 catch (      Exception e) {
        temp[0]=e;
      }
    }
  }
,true);
  if (temp[0] != null) {
    throw temp[0];
  }
}
