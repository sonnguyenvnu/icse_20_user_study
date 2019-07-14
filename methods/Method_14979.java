/** 
 * ????
 * @param sdf
 * @param user
 */
public void saveUser(SharedPreferences sdf,User user){
  if (sdf == null || user == null) {
    Log.e(TAG,"saveUser sdf == null || user == null >> return;");
    return;
  }
  String key=StringUtil.getTrimedString(user.getId());
  Log.i(TAG,"saveUser  key = user.getId() = " + user.getId());
  sdf.edit().remove(key).putString(key,JSON.toJSONString(user)).commit();
}
