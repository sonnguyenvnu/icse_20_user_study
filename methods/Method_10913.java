/** 
 * ??JSON????
 * @param context ???
 * @param key ??
 * @return
 */
public static String readJSONCache(Context context,String key){
  SharedPreferences sp=context.getSharedPreferences(JSON_CACHE,Context.MODE_PRIVATE);
  String jsoncache=sp.getString(key,null);
  return jsoncache;
}
