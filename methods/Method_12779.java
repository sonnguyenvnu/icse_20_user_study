public static int getSoVersion(Context context,String name){
  SharedPreferences preferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
  return preferences.getInt(name,0);
}
