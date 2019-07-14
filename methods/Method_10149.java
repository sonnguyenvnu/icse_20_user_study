/** 
 * Determines whether the specified user dose vote the specified entity.
 * @param userId the specified user id
 * @param dataId the specified entity id
 * @return voted type, returns {@code -1} if has not voted yet
 */
public int isVoted(final String userId,final String dataId){
  try {
    final List<Filter> filters=new ArrayList<>();
    filters.add(new PropertyFilter(Vote.USER_ID,FilterOperator.EQUAL,userId));
    filters.add(new PropertyFilter(Vote.DATA_ID,FilterOperator.EQUAL,dataId));
    final Query query=new Query().setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters));
    final JSONObject result=voteRepository.get(query);
    final JSONArray array=result.optJSONArray(Keys.RESULTS);
    if (0 == array.length()) {
      return -1;
    }
    final JSONObject vote=array.optJSONObject(0);
    return vote.optInt(Vote.TYPE);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,e.getMessage());
    return -1;
  }
}
