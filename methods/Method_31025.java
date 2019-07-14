@NonNull public static SharedPreferences getSharedPrefs(){
  return PreferenceManager.getDefaultSharedPreferences(DouyaApplication.getInstance());
}
