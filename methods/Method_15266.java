public static SharedPreferences getRootSharedPreferences(){
  return context.getSharedPreferences(ROOT_SHARE_PREFS_,Context.MODE_PRIVATE);
}
