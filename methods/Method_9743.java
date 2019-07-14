/** 
 * Shows audit log.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/auditlog",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showAuditlog(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/auditlog.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final int pageNum=Paginator.getPage(request);
  final int pageSize=PAGE_SIZE;
  final int windowSize=WINDOW_SIZE;
  final JSONObject requestJSONObject=new JSONObject();
  requestJSONObject.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,pageNum);
  requestJSONObject.put(Pagination.PAGINATION_PAGE_SIZE,pageSize);
  requestJSONObject.put(Pagination.PAGINATION_WINDOW_SIZE,windowSize);
  final JSONObject result=operationQueryService.getAuditlogs(requestJSONObject);
  dataModel.put(Operation.OPERATIONS,CollectionUtils.jsonArrayToList(result.optJSONArray(Operation.OPERATIONS)));
  final JSONObject pagination=result.optJSONObject(Pagination.PAGINATION);
  final int pageCount=pagination.optInt(Pagination.PAGINATION_PAGE_COUNT);
  final JSONArray pageNums=pagination.optJSONArray(Pagination.PAGINATION_PAGE_NUMS);
  dataModel.put(Pagination.PAGINATION_FIRST_PAGE_NUM,pageNums.opt(0));
  dataModel.put(Pagination.PAGINATION_LAST_PAGE_NUM,pageNums.opt(pageNums.length() - 1));
  dataModel.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,pageNum);
  dataModel.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  dataModel.put(Pagination.PAGINATION_PAGE_NUMS,CollectionUtils.jsonArrayToList(pageNums));
  dataModelService.fillHeaderAndFooter(context,dataModel);
}
