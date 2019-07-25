public static boolean set(String key,boolean value){
  SharedPreferences.Editor editor=mPreferences.edit();
  editor.putBoolean(key,value);
  return editor.commit();
}
