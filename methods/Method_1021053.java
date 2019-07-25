/** 
 * ???????????????????????
 * @param key
 * @param value
 */
public static void put(String key,String value){
  SharedPreferences.Editor editor=obtainPrefEditor();
  editor.putString(key,value);
  SharedPreferencesCompat.apply(editor);
}
