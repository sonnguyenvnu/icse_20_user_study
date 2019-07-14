/** 
 * Gets a user by the specified name.
 * @param name the specified name
 * @return user, returns {@code null} if not found
 */
public JSONObject getUserByName(final String name){
  try {
    final JSONObject ret=userRepository.getByName(name);
    if (null == ret) {
      return null;
    }
    final int point=ret.optInt(UserExt.USER_POINT);
    final int appRole=ret.optInt(UserExt.USER_APP_ROLE);
    if (UserExt.USER_APP_ROLE_C_HACKER == appRole) {
      ret.put(UserExt.USER_T_POINT_HEX,Integer.toHexString(point));
    }
 else {
      ret.put(UserExt.USER_T_POINT_CC,UserExt.toCCString(point));
    }
    return ret;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets user by name[" + name + "] failed",e);
    return null;
  }
}
