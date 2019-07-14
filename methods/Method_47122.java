private boolean getSecurePreference(){
  return mainActivity.getPrefs().getBoolean(FtpService.KEY_PREFERENCE_SECURE,FtpService.DEFAULT_SECURE);
}
