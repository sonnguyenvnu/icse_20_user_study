private void setFTPUsername(String username){
  mainActivity.getPrefs().edit().putString(FtpService.KEY_PREFERENCE_USERNAME,username).apply();
  updateStatus();
}
