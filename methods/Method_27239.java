public static void clearPrefs(){
  PreferenceManager.getDefaultSharedPreferences(App.getInstance()).edit().clear().apply();
}
