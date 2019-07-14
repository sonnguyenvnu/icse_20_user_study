/** 
 * Fills the specified user thumbnail URL.
 * @param user the specified user
 */
public void fillUserAvatarURL(final JSONObject user){
  user.put(UserExt.USER_AVATAR_URL + "210",getAvatarURLByUser(user,"210"));
  user.put(UserExt.USER_AVATAR_URL + "48",getAvatarURLByUser(user,"48"));
  user.put(UserExt.USER_AVATAR_URL + "20",getAvatarURLByUser(user,"20"));
}
