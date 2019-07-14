@Override public void initData(){
  tvAboutAppInfo.setText(APIJSONApplication.getInstance().getAppName() + "\n" + APIJSONApplication.getInstance().getAppVersion());
  setQRCode();
}
