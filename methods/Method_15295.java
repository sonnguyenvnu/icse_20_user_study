/** 
 * @param key
 * @param value
 */
public static void putBoolean(String key,boolean value){
  int keyIndex=getKeyIndex(key);
  if (keyIndex <= 0) {
    Log.e(TAG,"writeBoolean  keyIndex <= 0 >> return;");
    return;
  }
  context.getSharedPreferences(APP_SETTING,Context.MODE_PRIVATE).edit().remove(key).putBoolean(key,value).commit();
  init(context);
}
