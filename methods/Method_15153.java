/** 
 * @param value
 */
public void saveCookie(String value){
  context.getSharedPreferences(KEY_COOKIE,Context.MODE_PRIVATE).edit().remove(KEY_COOKIE).putString(KEY_COOKIE,value).commit();
}
