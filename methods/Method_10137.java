/** 
 * Gets users by the specified request json object.
 * @param requestJSONObject the specified request json object, for example,"userCity": "", "userLatestLoginTime": long, // optional, default to 0 "paginationCurrentPageNum": 1, "paginationPageSize": 20, "paginationWindowSize": 10, }, see  {@link Pagination} for more details
 * @return for example,      <pre>{ "pagination": { "paginationPageCount": 100, "paginationPageNums": [1, 2, 3, 4, 5] }, "users": [{ "oId": "", "userName": "", "userEmail": "", "userPassword": "", "roleName": "", .... }, ....] } </pre>
 * @see Pagination
 */
public JSONObject getUsersByCity(final JSONObject requestJSONObject){
  final JSONObject ret=new JSONObject();
  final int currentPageNum=requestJSONObject.optInt(Pagination.PAGINATION_CURRENT_PAGE_NUM);
  final int pageSize=requestJSONObject.optInt(Pagination.PAGINATION_PAGE_SIZE);
  final int windowSize=requestJSONObject.optInt(Pagination.PAGINATION_WINDOW_SIZE);
  final String city=requestJSONObject.optString(UserExt.USER_CITY);
  final long latestTime=requestJSONObject.optLong(UserExt.USER_LATEST_LOGIN_TIME);
  final Query query=new Query().addSort(UserExt.USER_LATEST_LOGIN_TIME,SortDirection.DESCENDING).setPage(currentPageNum,pageSize).setFilter(CompositeFilterOperator.and(new PropertyFilter(UserExt.USER_CITY,FilterOperator.EQUAL,city),new PropertyFilter(UserExt.USER_GEO_STATUS,FilterOperator.EQUAL,UserExt.USER_GEO_STATUS_C_PUBLIC),new PropertyFilter(UserExt.USER_STATUS,FilterOperator.EQUAL,UserExt.USER_STATUS_C_VALID),new PropertyFilter(UserExt.USER_LATEST_LOGIN_TIME,FilterOperator.GREATER_THAN_OR_EQUAL,latestTime)));
  JSONObject result;
  try {
    result=userRepository.get(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets users by city error",e);
    return null;
  }
  final int pageCount=result.optJSONObject(Pagination.PAGINATION).optInt(Pagination.PAGINATION_PAGE_COUNT);
  final JSONObject pagination=new JSONObject();
  ret.put(Pagination.PAGINATION,pagination);
  final List<Integer> pageNums=Paginator.paginate(currentPageNum,pageSize,pageCount,windowSize);
  pagination.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  pagination.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  final JSONArray users=result.optJSONArray(Keys.RESULTS);
  try {
    for (int i=0; i < users.length(); i++) {
      JSONObject user=users.getJSONObject(i);
      users.getJSONObject(i).put(Common.IS_FOLLOWING,followRepository.exists(requestJSONObject.optString(Keys.OBJECT_ID),user.optString(Keys.OBJECT_ID),Follow.FOLLOWING_TYPE_C_USER));
    }
  }
 catch (  final RepositoryException|JSONException e) {
    LOGGER.log(Level.ERROR,"Fills following failed",e);
  }
  ret.put(User.USERS,users);
  return ret;
}
