/** 
 * ??SharedPreferences??
 * @param context
 * @param sdf
 * @param key
 * @param value
 */
public static void save(SharedPreferences sdf,String key,String value){
  if (sdf == null || StringUtil.isNotEmpty(key,false) == false || StringUtil.isNotEmpty(value,false) == false) {
    Log.e(TAG,"save sdf == null || \n key = " + key + ";\n value = " + value + "\n >> return;");
    return;
  }
  sdf.edit().remove(key).putString(key,value).commit();
}
