public static boolean set(String key,String value){
  SharedPreferences.Editor editor=mPreferences.edit();
  editor.putString(key,value);
  return editor.commit();
}
