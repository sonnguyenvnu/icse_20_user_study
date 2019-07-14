/** 
 * Gets user link with the specified user.
 * @param user the specified user
 * @return user link
 */
public static String getUserLink(final JSONObject user){
  return getUserLink(user.optString(User.USER_NAME));
}
