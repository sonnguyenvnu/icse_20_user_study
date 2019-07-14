/** 
 * Shows user home following articles page.
 * @param context the specified context
 */
@RequestProcessing(value="/member/{userName}/following/articles",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,AnonymousViewCheck.class,UserBlockCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showHomeFollowingArticles(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  final HttpServletResponse response=context.getResponse();
  final JSONObject user=(JSONObject)context.attr(User.USER);
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"home/following-articles.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  dataModelService.fillHeaderAndFooter(context,dataModel);
  final int pageNum=Paginator.getPage(request);
  final int pageSize=Symphonys.USER_HOME_LIST_CNT;
  final int windowSize=Symphonys.USER_HOME_LIST_WIN_SIZE;
  fillHomeUser(dataModel,user,roleQueryService);
  final String followingId=user.optString(Keys.OBJECT_ID);
  dataModel.put(Follow.FOLLOWING_ID,followingId);
  avatarQueryService.fillUserAvatarURL(user);
  final JSONObject followingArticlesResult=followQueryService.getFollowingArticles(followingId,pageNum,pageSize);
  final List<JSONObject> followingArticles=(List<JSONObject>)followingArticlesResult.opt(Keys.RESULTS);
  dataModel.put(Common.USER_HOME_FOLLOWING_ARTICLES,followingArticles);
  final boolean isLoggedIn=(Boolean)dataModel.get(Common.IS_LOGGED_IN);
  if (isLoggedIn) {
    final JSONObject currentUser=Sessions.getUser();
    final String followerId=currentUser.optString(Keys.OBJECT_ID);
    final boolean isFollowing=followQueryService.isFollowing(followerId,followingId,Follow.FOLLOWING_TYPE_C_USER);
    dataModel.put(Common.IS_FOLLOWING,isFollowing);
    for (    final JSONObject followingArticle : followingArticles) {
      final String homeUserFollowingArticleId=followingArticle.optString(Keys.OBJECT_ID);
      followingArticle.put(Common.IS_FOLLOWING,followQueryService.isFollowing(followerId,homeUserFollowingArticleId,Follow.FOLLOWING_TYPE_C_ARTICLE));
    }
  }
  final int followingArticleCnt=followingArticlesResult.optInt(Pagination.PAGINATION_RECORD_COUNT);
  final int pageCount=(int)Math.ceil(followingArticleCnt / (double)pageSize);
  final List<Integer> pageNums=Paginator.paginate(pageNum,pageSize,pageCount,windowSize);
  if (!pageNums.isEmpty()) {
    dataModel.put(Pagination.PAGINATION_FIRST_PAGE_NUM,pageNums.get(0));
    dataModel.put(Pagination.PAGINATION_LAST_PAGE_NUM,pageNums.get(pageNums.size() - 1));
  }
  dataModel.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,pageNum);
  dataModel.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  dataModel.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  dataModel.put(Pagination.PAGINATION_RECORD_COUNT,followingArticleCnt);
  dataModel.put(Common.TYPE,"followingArticles");
}
