@Override public void initData(){
  showProgressDialog(R.string.loading);
  runThread(TAG + "initData",new Runnable(){
    @Override public void run(){
      settings=SettingUtil.getAllBooleans(context);
      runUiThread(new Runnable(){
        @Override public void run(){
          dismissProgressDialog();
          if (settings == null || settings.length <= 0) {
            finish();
            return;
          }
          for (int i=0; i < settings.length; i++) {
            setSwitch(i,settings[i]);
          }
        }
      }
);
    }
  }
);
}
