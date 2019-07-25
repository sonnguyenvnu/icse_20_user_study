public static void put(String key,int value){
  SharedPreferences.Editor editor=obtainPrefEditor();
  editor.putInt(key,value);
  SharedPreferencesCompat.apply(editor);
}
