/** 
 * Show city users.
 * @param context the specified context
 */
@RequestProcessing(value="/city/{city}/users",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,LoginCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showCityUsers(final RequestContext context){
  final String city=context.pathVar("city");
  final HttpServletRequest request=context.getRequest();
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"city.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  dataModelService.fillHeaderAndFooter(context,dataModel);
  dataModel.put(Common.CURRENT,"/users");
  dataModelService.fillRandomArticles(dataModel);
  dataModelService.fillSideHotArticles(dataModel);
  dataModelService.fillSideTags(dataModel);
  dataModelService.fillLatestCmts(dataModel);
  List<JSONObject> users=new ArrayList<>();
  dataModel.put(User.USERS,users);
  dataModel.put(Common.SELECTED,Common.CITY);
  final JSONObject user=Sessions.getUser();
  if (!UserExt.finshedGuide(user)) {
    context.sendRedirect(Latkes.getServePath() + "/guide");
    return;
  }
  dataModel.put(UserExt.USER_GEO_STATUS,true);
  dataModel.put(Common.CITY_FOUND,true);
  dataModel.put(Common.CITY,langService.get("sameCityLabel"));
  if (UserExt.USER_GEO_STATUS_C_PUBLIC != user.optInt(UserExt.USER_GEO_STATUS)) {
    dataModel.put(UserExt.USER_GEO_STATUS,false);
    return;
  }
  final String userCity=user.optString(UserExt.USER_CITY);
  String queryCity=city;
  if ("my".equals(city)) {
    dataModel.put(Common.CITY,userCity);
    queryCity=userCity;
  }
 else {
    dataModel.put(Common.CITY,city);
  }
  if (StringUtils.isBlank(userCity)) {
    dataModel.put(Common.CITY_FOUND,false);
    return;
  }
  final int pageNum=Paginator.getPage(request);
  final int pageSize=Symphonys.CITY_USERS_CNT;
  final int windowSize=Symphonys.CITY_USERS_WIN_SIZE;
  final JSONObject requestJSONObject=new JSONObject();
  requestJSONObject.put(Keys.OBJECT_ID,user.optString(Keys.OBJECT_ID));
  requestJSONObject.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,pageNum);
  requestJSONObject.put(Pagination.PAGINATION_PAGE_SIZE,pageSize);
  requestJSONObject.put(Pagination.PAGINATION_WINDOW_SIZE,windowSize);
  final long latestLoginTime=DateUtils.addDays(new Date(),Integer.MIN_VALUE).getTime();
  requestJSONObject.put(UserExt.USER_LATEST_LOGIN_TIME,latestLoginTime);
  requestJSONObject.put(UserExt.USER_CITY,queryCity);
  final JSONObject result=userQueryService.getUsersByCity(requestJSONObject);
  final JSONArray cityUsers=result.optJSONArray(User.USERS);
  final JSONObject pagination=result.optJSONObject(Pagination.PAGINATION);
  if (null != cityUsers && cityUsers.length() > 0) {
    for (int i=0; i < cityUsers.length(); i++) {
      users.add(cityUsers.getJSONObject(i));
    }
    dataModel.put(User.USERS,users);
  }
  final int pageCount=pagination.optInt(Pagination.PAGINATION_PAGE_COUNT);
  final List<Integer> pageNums=Paginator.paginate(pageNum,pageSize,pageCount,windowSize);
  if (!pageNums.isEmpty()) {
    dataModel.put(Pagination.PAGINATION_FIRST_PAGE_NUM,pageNums.get(0));
    dataModel.put(Pagination.PAGINATION_LAST_PAGE_NUM,pageNums.get(pageNums.size() - 1));
  }
  dataModel.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,pageNum);
  dataModel.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  dataModel.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
}
