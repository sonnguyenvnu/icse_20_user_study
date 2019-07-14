/** 
 * Shows user home following users page.
 * @param context the specified context
 */
@RequestProcessing(value="/member/{userName}/following/users",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,AnonymousViewCheck.class,UserBlockCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showHomeFollowingUsers(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  final JSONObject user=(JSONObject)context.attr(User.USER);
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"home/following-users.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  dataModelService.fillHeaderAndFooter(context,dataModel);
  final int pageNum=Paginator.getPage(request);
  final int pageSize=Symphonys.USER_HOME_LIST_CNT;
  final int windowSize=Symphonys.USER_HOME_LIST_WIN_SIZE;
  fillHomeUser(dataModel,user,roleQueryService);
  final String followingId=user.optString(Keys.OBJECT_ID);
  dataModel.put(Follow.FOLLOWING_ID,followingId);
  avatarQueryService.fillUserAvatarURL(user);
  final JSONObject followingUsersResult=followQueryService.getFollowingUsers(followingId,pageNum,pageSize);
  final List<JSONObject> followingUsers=(List<JSONObject>)followingUsersResult.opt(Keys.RESULTS);
  dataModel.put(Common.USER_HOME_FOLLOWING_USERS,followingUsers);
  final boolean isLoggedIn=(Boolean)dataModel.get(Common.IS_LOGGED_IN);
  if (isLoggedIn) {
    final JSONObject currentUser=Sessions.getUser();
    final String followerId=currentUser.optString(Keys.OBJECT_ID);
    final boolean isFollowing=followQueryService.isFollowing(followerId,followingId,Follow.FOLLOWING_TYPE_C_USER);
    dataModel.put(Common.IS_FOLLOWING,isFollowing);
    for (    final JSONObject followingUser : followingUsers) {
      final String homeUserFollowingUserId=followingUser.optString(Keys.OBJECT_ID);
      followingUser.put(Common.IS_FOLLOWING,followQueryService.isFollowing(followerId,homeUserFollowingUserId,Follow.FOLLOWING_TYPE_C_USER));
    }
  }
  final int followingUserCnt=followingUsersResult.optInt(Pagination.PAGINATION_RECORD_COUNT);
  final int pageCount=(int)Math.ceil((double)followingUserCnt / (double)pageSize);
  final List<Integer> pageNums=Paginator.paginate(pageNum,pageSize,pageCount,windowSize);
  if (!pageNums.isEmpty()) {
    dataModel.put(Pagination.PAGINATION_FIRST_PAGE_NUM,pageNums.get(0));
    dataModel.put(Pagination.PAGINATION_LAST_PAGE_NUM,pageNums.get(pageNums.size() - 1));
  }
  dataModel.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,pageNum);
  dataModel.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  dataModel.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  dataModel.put(Pagination.PAGINATION_RECORD_COUNT,followingUserCnt);
  dataModel.put(Common.TYPE,"followingUsers");
}
