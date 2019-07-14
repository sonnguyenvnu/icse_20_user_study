/** 
 * @param tag
 * @param value
 */
public void saveToken(String tag,String value){
  context.getSharedPreferences(KEY_TOKEN,Context.MODE_PRIVATE).edit().remove(KEY_TOKEN + tag).putString(KEY_TOKEN + tag,value).commit();
}
