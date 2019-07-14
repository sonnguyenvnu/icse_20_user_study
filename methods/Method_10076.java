/** 
 * Gets the reserved words.
 * @return reserved words
 */
public List<JSONObject> getReservedWords(){
  final Query query=new Query().setFilter(new PropertyFilter(Option.OPTION_CATEGORY,FilterOperator.EQUAL,Option.CATEGORY_C_RESERVED_WORDS));
  try {
    final JSONObject result=optionRepository.get(query);
    final JSONArray options=result.optJSONArray(Keys.RESULTS);
    return CollectionUtils.jsonArrayToList(options);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets reserved words failed",e);
    return Collections.emptyList();
  }
}
