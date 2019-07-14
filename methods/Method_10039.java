/** 
 * Gets an invitecode with the specified code.
 * @param code the specified code
 * @return invitecode, returns {@code null} if not found
 */
public JSONObject getInvitecode(final String code){
  final Query query=new Query().setFilter(new PropertyFilter(Invitecode.CODE,FilterOperator.EQUAL,code));
  try {
    final JSONObject result=invitecodeRepository.get(query);
    final JSONArray codes=result.optJSONArray(Keys.RESULTS);
    if (0 == codes.length()) {
      return null;
    }
    return codes.optJSONObject(0);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets invitecode error",e);
    return null;
  }
}
