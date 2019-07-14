/** 
 * Gets all permissions and marks grant of a user specified by the given user id.
 * @param userId the given user id
 * @return a list of permissions, returns an empty list if not found
 */
public List<JSONObject> getUserPermissionsGrant(final String userId){
  try {
    final JSONObject user=userRepository.get(userId);
    if (null == user) {
      return getPermissionsGrant(Role.ROLE_ID_C_VISITOR);
    }
    final String roleId=user.optString(User.USER_ROLE);
    return getPermissionsGrant(roleId);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets user permissions grant failed",e);
    return getPermissionsGrant(Role.ROLE_ID_C_VISITOR);
  }
}
