private void changeFTPServerPort(int port){
  mainActivity.getPrefs().edit().putInt(FtpService.PORT_PREFERENCE_KEY,port).apply();
  updateSpans();
  updateStatus();
}
