/** 
 * Gets users by the specified request json object.
 * @param requestJSONObject the specified request json object, for example,"userNameOrEmail": "", // optional "paginationCurrentPageNum": 1, "paginationPageSize": 20, "paginationWindowSize": 10, , see  {@link Pagination} for more details
 * @return for example,      <pre>{ "pagination": { "paginationPageCount": 100, "paginationPageNums": [1, 2, 3, 4, 5] }, "users": [{ "oId": "", "userName": "", "userEmail": "", "userPassword": "", "roleName": "", .... }, ....] } </pre>
 * @see Pagination
 */
public JSONObject getUsers(final JSONObject requestJSONObject){
  final JSONObject ret=new JSONObject();
  final int currentPageNum=requestJSONObject.optInt(Pagination.PAGINATION_CURRENT_PAGE_NUM);
  final int pageSize=requestJSONObject.optInt(Pagination.PAGINATION_PAGE_SIZE);
  final int windowSize=requestJSONObject.optInt(Pagination.PAGINATION_WINDOW_SIZE);
  final Query query=new Query().addSort(Keys.OBJECT_ID,SortDirection.DESCENDING).setPage(currentPageNum,pageSize);
  if (requestJSONObject.has(Common.QUERY)) {
    final String q=requestJSONObject.optString(Common.QUERY);
    final List<Filter> filters=new ArrayList<>();
    filters.add(new PropertyFilter(User.USER_NAME,FilterOperator.EQUAL,q));
    filters.add(new PropertyFilter(User.USER_EMAIL,FilterOperator.EQUAL,q));
    filters.add(new PropertyFilter(Keys.OBJECT_ID,FilterOperator.EQUAL,q));
    query.setFilter(new CompositeFilter(CompositeFilterOperator.OR,filters));
  }
  JSONObject result;
  try {
    result=userRepository.get(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets users failed",e);
    return null;
  }
  final int pageCount=result.optJSONObject(Pagination.PAGINATION).optInt(Pagination.PAGINATION_PAGE_COUNT);
  final JSONObject pagination=new JSONObject();
  ret.put(Pagination.PAGINATION,pagination);
  final List<Integer> pageNums=Paginator.paginate(currentPageNum,pageSize,pageCount,windowSize);
  pagination.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  pagination.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  final JSONArray users=result.optJSONArray(Keys.RESULTS);
  ret.put(User.USERS,users);
  for (int i=0; i < users.length(); i++) {
    final JSONObject user=users.optJSONObject(i);
    user.put(UserExt.USER_T_CREATE_TIME,new Date(user.optLong(Keys.OBJECT_ID)));
    avatarQueryService.fillUserAvatarURL(user);
    final JSONObject role=roleQueryService.getRole(user.optString(User.USER_ROLE));
    user.put(Role.ROLE_NAME,role.optString(Role.ROLE_NAME));
  }
  return ret;
}
