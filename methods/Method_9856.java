/** 
 * Shows user home breezemoons page.
 * @param context the specified context
 */
@RequestProcessing(value={"/member/{userName}/breezemoons","/member/{userName}/breezemoons/{breezemoonId}"},method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,AnonymousViewCheck.class,UserBlockCheck.class}) @After({CSRFToken.class,PermissionGrant.class,StopwatchEndAdvice.class}) public void showHomeBreezemoons(final RequestContext context){
  final String breezemoonId=context.pathVar("breezemoonId");
  final HttpServletRequest request=context.getRequest();
  final JSONObject user=(JSONObject)context.attr(User.USER);
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"home/breezemoons.ftl");
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
  JSONObject currentUser;
  String currentUserId=null;
  if (isLoggedIn) {
    currentUser=Sessions.getUser();
    currentUserId=currentUser.optString(Keys.OBJECT_ID);
    final boolean isFollowing=followQueryService.isFollowing(currentUserId,followingId,Follow.FOLLOWING_TYPE_C_USER);
    dataModel.put(Common.IS_FOLLOWING,isFollowing);
  }
  final JSONObject result=breezemoonQueryService.getBreezemoons(currentUserId,followingId,pageNum,pageSize,windowSize);
  List<JSONObject> bms=(List<JSONObject>)result.opt(Breezemoon.BREEZEMOONS);
  dataModel.put(Common.USER_HOME_BREEZEMOONS,bms);
  final JSONObject pagination=result.optJSONObject(Pagination.PAGINATION);
  final int recordCount=pagination.optInt(Pagination.PAGINATION_RECORD_COUNT);
  final int pageCount=(int)Math.ceil(recordCount / (double)pageSize);
  final List<Integer> pageNums=Paginator.paginate(pageNum,pageSize,pageCount,windowSize);
  if (!pageNums.isEmpty()) {
    dataModel.put(Pagination.PAGINATION_FIRST_PAGE_NUM,pageNums.get(0));
    dataModel.put(Pagination.PAGINATION_LAST_PAGE_NUM,pageNums.get(pageNums.size() - 1));
  }
  dataModel.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,pageNum);
  dataModel.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  dataModel.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  dataModel.put(Pagination.PAGINATION_RECORD_COUNT,recordCount);
  dataModel.put(Common.TYPE,Breezemoon.BREEZEMOONS);
  if (StringUtils.isNotBlank(breezemoonId)) {
    dataModel.put(Common.IS_SINGLE_BREEZEMOON_URL,true);
    final JSONObject breezemoon=breezemoonQueryService.getBreezemoon(breezemoonId);
    if (null == breezemoon) {
      context.sendError(HttpServletResponse.SC_NOT_FOUND);
      return;
    }
    bms=Collections.singletonList(breezemoon);
    breezemoonQueryService.organizeBreezemoons("admin",bms);
    dataModel.put(Common.USER_HOME_BREEZEMOONS,bms);
  }
 else {
    dataModel.put(Common.IS_SINGLE_BREEZEMOON_URL,false);
  }
}
