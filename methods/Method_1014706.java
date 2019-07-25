/** 
 * ????key??????
 * @param context
 * @param key
 * @return
 */
public static boolean contains(Context context,String key){
  SharedPreferences sp=context.getSharedPreferences(SharedFILE_NAME,Context.MODE_PRIVATE);
  return sp.contains(key);
}
