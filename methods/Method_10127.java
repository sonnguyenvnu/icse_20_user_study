/** 
 * Gets latest logged in users by the specified time.
 * @param time           the specified start time
 * @param currentPageNum the specified current page number
 * @param pageSize       the specified page size
 * @param windowSize     the specified window size
 * @return for example,      <pre>{ "pagination": { "paginationPageCount": 100, "paginationPageNums": [1, 2, 3, 4, 5] }, "users": [{ "oId": "", "userName": "", "userEmail": "", "userPassword": "", "roleName": "", .... }, ....] } </pre>
 * @throws ServiceException service exception
 * @see Pagination
 */
public JSONObject getLatestLoggedInUsers(final long time,final int currentPageNum,final int pageSize,final int windowSize) throws ServiceException {
  final JSONObject ret=new JSONObject();
  final Query query=new Query().addSort(Keys.OBJECT_ID,SortDirection.DESCENDING).setPage(currentPageNum,pageSize).setFilter(CompositeFilterOperator.and(new PropertyFilter(UserExt.USER_STATUS,FilterOperator.EQUAL,UserExt.USER_STATUS_C_VALID),new PropertyFilter(UserExt.USER_LATEST_LOGIN_TIME,FilterOperator.GREATER_THAN_OR_EQUAL,time)));
  JSONObject result;
  try {
    result=userRepository.get(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets latest logged in user failed",e);
    throw new ServiceException(e);
  }
  final int pageCount=result.optJSONObject(Pagination.PAGINATION).optInt(Pagination.PAGINATION_PAGE_COUNT);
  final JSONObject pagination=new JSONObject();
  ret.put(Pagination.PAGINATION,pagination);
  final List<Integer> pageNums=Paginator.paginate(currentPageNum,pageSize,pageCount,windowSize);
  pagination.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  pagination.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  final JSONArray users=result.optJSONArray(Keys.RESULTS);
  ret.put(User.USERS,users);
  return ret;
}
