/** 
 * Gets an invitecode by the specified user id.
 * @param userId the specified user id
 * @return for example,      <pre>{ "oId": "", "code": "", "memo": "", .... } </pre>, returns  {@code null} if not found
 * @throws ServiceException service exception
 */
public JSONObject getInvitecodeByUserId(final String userId) throws ServiceException {
  final Query query=new Query().setFilter(new PropertyFilter(Invitecode.USER_ID,FilterOperator.EQUAL,userId));
  try {
    final JSONArray data=invitecodeRepository.get(query).optJSONArray(Keys.RESULTS);
    if (1 > data.length()) {
      return null;
    }
    return data.optJSONObject(0);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets an invitecode failed",e);
    throw new ServiceException(e);
  }
}
