public static void save(ArrayList<String> drafts){
  SharedPreferences.Editor e=Authentication.authentication.edit();
  e.putString(SettingValues.PREF_DRAFTS,Reddit.arrayToString(drafts,"</newdraft>"));
  e.commit();
}
