private void changeFTPServerPath(String path){
  SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(getActivity());
  preferences.edit().putString(FtpService.KEY_PREFERENCE_PATH,path).apply();
  updateStatus();
}
