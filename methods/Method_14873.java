@Override public void finish(){
  if (isSettingChanged) {
    showProgressDialog("??????????...");
    runThread(TAG,new Runnable(){
      @Override public void run(){
        SettingUtil.putAllBoolean(settings);
        isSettingChanged=false;
        runUiThread(new Runnable(){
          @Override public void run(){
            SettingActivity.this.finish();
          }
        }
);
      }
    }
);
    return;
  }
  super.finish();
}
