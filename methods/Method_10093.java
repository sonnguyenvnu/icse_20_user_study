/** 
 * Checks whether the specified user has the specified requisite permissions.
 * @param userId               the specified user id
 * @param requisitePermissions the specified requisite permissions
 * @return @code true} if the role has the specified requisite permissions, returns @code false} otherwise
 */
public boolean userHasPermissions(final String userId,final Set<String> requisitePermissions){
  try {
    final JSONObject user=userRepository.get(userId);
    final String roleId=user.optString(User.USER_ROLE);
    final Set<String> permissions=getPermissions(roleId);
    return Permission.hasPermission(requisitePermissions,permissions);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Checks user [" + userId + "] has permission failed",e);
    return false;
  }
}
