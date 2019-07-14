/** 
 * Gets the top eating snake users (single game max) with the specified fetch size.
 * @param fetchSize the specified fetch size
 * @return users, returns an empty list if not found
 */
public List<JSONObject> getTopEatingSnakeUsersMax(final int fetchSize){
  final List<JSONObject> ret=new ArrayList<>();
  try {
    final List<JSONObject> users=userRepository.select("SELECT\n" + "	u.*, MAX(sum) AS point\n" + "FROM\n" + "	" + pointtransferRepository.getName() + " AS p,\n" + "	" + userRepository.getName() + " AS u\n" + "WHERE\n" + "	p.toId = u.oId\n" + "AND type = 27\n" + "GROUP BY\n" + "	toId\n" + "ORDER BY\n" + "	point DESC\n" + "LIMIT ?",fetchSize);
    for (    final JSONObject user : users) {
      avatarQueryService.fillUserAvatarURL(user);
      ret.add(user);
    }
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets top eating snake users error",e);
  }
  return ret;
}
