/** 
 * Removes vote if it exists.
 * @param userId   the specified user id
 * @param dataId   the specified data entity id
 * @param dataType the specified data type
 * @return the removed vote type, returns {@code -1} if removed nothing
 * @throws RepositoryException repository exception
 */
public int removeIfExists(final String userId,final String dataId,final int dataType) throws RepositoryException {
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Vote.USER_ID,FilterOperator.EQUAL,userId));
  filters.add(new PropertyFilter(Vote.DATA_ID,FilterOperator.EQUAL,dataId));
  filters.add(new PropertyFilter(Vote.DATA_TYPE,FilterOperator.EQUAL,dataType));
  final Query query=new Query().setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters));
  final JSONObject result=get(query);
  final JSONArray array=result.optJSONArray(Keys.RESULTS);
  if (0 == array.length()) {
    return -1;
  }
  final JSONObject voteToRemove=array.optJSONObject(0);
  remove(voteToRemove.optString(Keys.OBJECT_ID));
  return voteToRemove.optInt(Vote.TYPE);
}
