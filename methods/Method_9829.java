/** 
 * Shows [point] notifications.
 * @param context the specified context
 */
@RequestProcessing(value="/notifications/point",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,LoginCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showPointNotifications(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  final JSONObject currentUser=Sessions.getUser();
  if (null == currentUser) {
    context.sendError(HttpServletResponse.SC_FORBIDDEN);
    return;
  }
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"home/notifications/point.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final String userId=currentUser.optString(Keys.OBJECT_ID);
  final int pageNum=Paginator.getPage(request);
  final int pageSize=Symphonys.NOTIFICATION_LIST_CNT;
  final int windowSize=Symphonys.NOTIFICATION_LIST_WIN_SIZE;
  final JSONObject result=notificationQueryService.getPointNotifications(userId,pageNum,pageSize);
  final List<JSONObject> pointNotifications=(List<JSONObject>)result.get(Keys.RESULTS);
  dataModel.put(Common.POINT_NOTIFICATIONS,pointNotifications);
  fillNotificationCount(userId,dataModel);
  notificationMgmtService.makeRead(pointNotifications);
  final int recordCnt=result.getInt(Pagination.PAGINATION_RECORD_COUNT);
  final int pageCount=(int)Math.ceil((double)recordCnt / (double)pageSize);
  final List<Integer> pageNums=Paginator.paginate(pageNum,pageSize,pageCount,windowSize);
  if (!pageNums.isEmpty()) {
    dataModel.put(Pagination.PAGINATION_FIRST_PAGE_NUM,pageNums.get(0));
    dataModel.put(Pagination.PAGINATION_LAST_PAGE_NUM,pageNums.get(pageNums.size() - 1));
  }
  dataModel.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,pageNum);
  dataModel.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  dataModel.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  dataModelService.fillHeaderAndFooter(context,dataModel);
}
