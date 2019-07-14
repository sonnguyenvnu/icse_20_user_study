/** 
 * Shows user home comments page.
 * @param context the specified context
 */
@RequestProcessing(value="/member/{userName}/comments",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,AnonymousViewCheck.class,UserBlockCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showHomeComments(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  final JSONObject user=(JSONObject)context.attr(User.USER);
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"home/comments.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  dataModelService.fillHeaderAndFooter(context,dataModel);
  final int pageNum=Paginator.getPage(request);
  final int pageSize=Symphonys.USER_HOME_LIST_CNT;
  final int windowSize=Symphonys.USER_HOME_LIST_WIN_SIZE;
  fillHomeUser(dataModel,user,roleQueryService);
  avatarQueryService.fillUserAvatarURL(user);
  final String followingId=user.optString(Keys.OBJECT_ID);
  dataModel.put(Follow.FOLLOWING_ID,followingId);
  final boolean isLoggedIn=(Boolean)dataModel.get(Common.IS_LOGGED_IN);
  JSONObject currentUser=null;
  if (isLoggedIn) {
    currentUser=Sessions.getUser();
    final String followerId=currentUser.optString(Keys.OBJECT_ID);
    final boolean isFollowing=followQueryService.isFollowing(followerId,followingId,Follow.FOLLOWING_TYPE_C_USER);
    dataModel.put(Common.IS_FOLLOWING,isFollowing);
  }
  final List<JSONObject> userComments=commentQueryService.getUserComments(user.optString(Keys.OBJECT_ID),Comment.COMMENT_ANONYMOUS_C_PUBLIC,pageNum,pageSize,currentUser);
  dataModel.put(Common.USER_HOME_COMMENTS,userComments);
  int recordCount=0;
  int pageCount=0;
  if (!userComments.isEmpty()) {
    final JSONObject first=userComments.get(0);
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
  dataModel.put(Common.TYPE,"comments");
}
