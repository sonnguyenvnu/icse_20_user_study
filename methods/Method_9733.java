/** 
 * Checks whether the specified user finshed guide.
 * @param user the specified user
 * @return {@code true} if the specified user finshed guide, returns {@code false} otherwise
 */
public static boolean finshedGuide(final JSONObject user){
  return UserExt.USER_GUIDE_STEP_FIN == user.optInt(UserExt.USER_GUIDE_STEP);
}
