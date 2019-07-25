public static boolean set(String key,int value){
  SharedPreferences.Editor editor=mPreferences.edit();
  editor.putInt(key,value);
  return editor.commit();
}
