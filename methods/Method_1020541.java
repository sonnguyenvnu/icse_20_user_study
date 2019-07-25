public static boolean set(String key,long value){
  SharedPreferences.Editor editor=mPreferences.edit();
  editor.putLong(key,value);
  return editor.commit();
}
