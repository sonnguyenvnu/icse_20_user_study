private static void setBoolean(Context context,String key,boolean value){
  context.getSharedPreferences(IMAGE_FORMAT_PREFS,Context.MODE_PRIVATE).edit().putBoolean(key,value).apply();
}
