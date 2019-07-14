/** 
 * ??????,??????????
 * @param user  user == null >> user = new User();
 */
public void saveCurrentUser(User user){
  SharedPreferences sdf=context.getSharedPreferences(PATH_USER,Context.MODE_PRIVATE);
  if (sdf == null) {
    Log.e(TAG,"saveUser sdf == null  >> return;");
    return;
  }
  SharedPreferences.Editor editor=sdf.edit();
  if (user != null) {
    editor.remove(KEY_LAST_USER_ID).putLong(KEY_LAST_USER_ID,user.getId());
  }
  editor.remove(KEY_CURRENT_USER_ID).putLong(KEY_CURRENT_USER_ID,user == null ? 0 : user.getId());
  editor.commit();
  saveUser(sdf,user);
}
