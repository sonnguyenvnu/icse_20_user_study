/** 
 * ????int?????
 */
public static void putInt(String key,int value){
  SharedPreferences.Editor editor=getSharedPreference(CONFIG).edit();
  editor.putInt(key,value).apply();
}
