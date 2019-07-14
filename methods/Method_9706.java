/** 
 * Puts the specified admins.
 * @param admins the specified admins
 */
public void putAdmins(final List<JSONObject> admins){
  ADMINS_CACHE.clear();
  ADMINS_CACHE.addAll(admins);
}
