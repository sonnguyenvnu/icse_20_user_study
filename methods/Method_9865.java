/** 
 * Shows user home follower users page.
 * @param context the specified context
 */
@RequestProcessing(value="/member/{userName}/followers",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,AnonymousViewCheck.class,UserBlockCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showHomeFollowers(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  final JSONObject user=(JSONObject)context.attr(User.USER);
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"home/followers.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  dataModelService.fillHeaderAndFooter(context,dataModel);
  final int pageNum=Paginator.getPage(request);
  final int pageSize=Symphonys.USER_HOME_LIST_CNT;
  final int windowSize=Symphonys.USER_HOME_LIST_WIN_SIZE;
  fillHomeUser(dataModel,user,roleQueryService);
  final String followingId=user.optString(Keys.OBJECT_ID);
  dataModel.put(Follow.FOLLOWING_ID,followingId);
  final JSONObject followerUsersResult=followQueryService.getFollowerUsers(followingId,pageNum,pageSize);
  final List<JSONObject> followerUsers=(List)followerUsersResult.opt(Keys.RESULTS);
  dataModel.put(Common.USER_HOME_FOLLOWER_USERS,followerUsers);
  avatarQueryService.fillUserAvatarURL(user);
  final boolean isLoggedIn=(Boolean)dataModel.get(Common.IS_LOGGED_IN);
  if (isLoggedIn) {
    final JSONObject currentUser=Sessions.getUser();
    final String followerId=currentUser.optString(Keys.OBJECT_ID);
    final boolean isFollowing=followQueryService.isFollowing(followerId,followingId,Follow.FOLLOWING_TYPE_C_USER);
    dataModel.put(Common.IS_FOLLOWING,isFollowing);
    for (    final JSONObject followerUser : followerUsers) {
      final String homeUserFollowerUserId=followerUser.optString(Keys.OBJECT_ID);
      followerUser.put(Common.IS_FOLLOWING,followQueryService.isFollowing(followerId,homeUserFollowerUserId,Follow.FOLLOWING_TYPE_C_USER));
    }
    if (followerId.equals(followingId)) {
      notificationMgmtService.makeRead(followingId,Notification.DATA_TYPE_C_NEW_FOLLOWER);
    }
  }
  final int followerUserCnt=followerUsersResult.optInt(Pagination.PAGINATION_RECORD_COUNT);
  final int pageCount=(int)Math.ceil((double)followerUserCnt / (double)pageSize);
  final List<Integer> pageNums=Paginator.paginate(pageNum,pageSize,pageCount,windowSize);
  if (!pageNums.isEmpty()) {
    dataModel.put(Pagination.PAGINATION_FIRST_PAGE_NUM,pageNums.get(0));
    dataModel.put(Pagination.PAGINATION_LAST_PAGE_NUM,pageNums.get(pageNums.size() - 1));
  }
  dataModel.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,pageNum);
  dataModel.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  dataModel.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  dataModel.put(Pagination.PAGINATION_RECORD_COUNT,followerUserCnt);
  dataModel.put(Common.TYPE,"followers");
  notificationMgmtService.makeRead(followingId,Notification.DATA_TYPE_C_NEW_FOLLOWER);
}
