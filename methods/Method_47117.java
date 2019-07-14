private String getUsernameFromPreferences(){
  return mainActivity.getPrefs().getString(FtpService.KEY_PREFERENCE_USERNAME,FtpService.DEFAULT_USERNAME);
}
