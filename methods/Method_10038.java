/** 
 * Gets valid invitecodes by the specified generator id.
 * @param generatorId the specified generator id
 * @return for example,      <pre>{ "oId": "", "code": "", "memo": "", .... } </pre>, returns an empty list if not found
 */
public List<JSONObject> getValidInvitecodes(final String generatorId){
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Invitecode.GENERATOR_ID,FilterOperator.EQUAL,generatorId),new PropertyFilter(Invitecode.STATUS,FilterOperator.EQUAL,Invitecode.STATUS_C_UNUSED)));
  try {
    return CollectionUtils.jsonArrayToList(invitecodeRepository.get(query).optJSONArray(Keys.RESULTS));
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets valid invitecode failed",e);
    return Collections.emptyList();
  }
}
