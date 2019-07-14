/** 
 * Shows user home page.
 * @param context the specified context
 */
@RequestProcessing(value="/member/{userName}",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,AnonymousViewCheck.class,UserBlockCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showHome(final RequestContext context){
  final String userName=context.pathVar("userName");
  final HttpServletRequest request=context.getRequest();
  final JSONObject user=(JSONObject)context.attr(User.USER);
  final int pageNum=Paginator.getPage(request);
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"home/home.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  dataModelService.fillHeaderAndFooter(context,dataModel);
  final String followingId=user.optString(Keys.OBJECT_ID);
  dataModel.put(Follow.FOLLOWING_ID,followingId);
  fillHomeUser(dataModel,user,roleQueryService);
  avatarQueryService.fillUserAvatarURL(user);
  final boolean isLoggedIn=(Boolean)dataModel.get(Common.IS_LOGGED_IN);
  if (isLoggedIn) {
    final JSONObject currentUser=Sessions.getUser();
    final String followerId=currentUser.optString(Keys.OBJECT_ID);
    final boolean isFollowing=followQueryService.isFollowing(followerId,followingId,Follow.FOLLOWING_TYPE_C_USER);
    dataModel.put(Common.IS_FOLLOWING,isFollowing);
  }
  final int pageSize=Symphonys.USER_HOME_LIST_CNT;
  final int windowSize=Symphonys.USER_HOME_LIST_WIN_SIZE;
  final List<JSONObject> userArticles=articleQueryService.getUserArticles(user.optString(Keys.OBJECT_ID),Article.ARTICLE_ANONYMOUS_C_PUBLIC,pageNum,pageSize);
  dataModel.put(Common.USER_HOME_ARTICLES,userArticles);
  int recordCount=0;
  int pageCount=0;
  if (!userArticles.isEmpty()) {
    final JSONObject first=userArticles.get(0);
    pageCount=first.optInt(Pagination.PAGINATION_PAGE_COUNT);
    recordCount=first.optInt(Pagination.PAGINATION_RECORD_COUNT);
  }
  final List<Integer> pageNums=Paginator.paginate(pageNum,pageSize,pageCount,windowSize);
  if (!pageNums.isEmpty()) {
    dataModel.put(Pagination.PAGINATION_FIRST_PAGE_NUM,pageNums.get(0));
    dataModel.put(Pagination.PAGINATION_LAST_PAGE_NUM,pageNums.get(pageNums.size() - 1));
  }
  dataModel.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,pageNum);
  dataModel.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  dataModel.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  dataModel.put(Pagination.PAGINATION_RECORD_COUNT,recordCount);
  final JSONObject currentUser=Sessions.getUser();
  if (null == currentUser) {
    dataModel.put(Common.IS_MY_ARTICLE,false);
  }
 else {
    dataModel.put(Common.IS_MY_ARTICLE,userName.equals(currentUser.optString(User.USER_NAME)));
  }
  dataModel.put(Common.TYPE,"home");
}
