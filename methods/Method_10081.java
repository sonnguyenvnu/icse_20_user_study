/** 
 * Gets the latest pointtransfers with the specified user id, type and fetch size.
 * @param userId    the specified user id
 * @param type      the specified type
 * @param fetchSize the specified fetch size
 * @return pointtransfers, returns an empty list if not found
 */
public List<JSONObject> getLatestPointtransfers(final String userId,final int type,final int fetchSize){
  final List<JSONObject> ret=new ArrayList<>();
  final List<Filter> userFilters=new ArrayList<>();
  userFilters.add(new PropertyFilter(Pointtransfer.FROM_ID,FilterOperator.EQUAL,userId));
  userFilters.add(new PropertyFilter(Pointtransfer.TO_ID,FilterOperator.EQUAL,userId));
  final List<Filter> filters=new ArrayList<>();
  filters.add(new CompositeFilter(CompositeFilterOperator.OR,userFilters));
  filters.add(new PropertyFilter(Pointtransfer.TYPE,FilterOperator.EQUAL,type));
  final Query query=new Query().addSort(Keys.OBJECT_ID,SortDirection.DESCENDING).setPage(1,fetchSize).setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters));
  try {
    final JSONObject result=pointtransferRepository.get(query);
    return CollectionUtils.jsonArrayToList(result.optJSONArray(Keys.RESULTS));
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets latest pointtransfers error",e);
  }
  return ret;
}
