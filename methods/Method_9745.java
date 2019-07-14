/** 
 * Show admin breezemoons.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/breezemoons",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showBreezemoons(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/breezemoons.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final int pageNum=Paginator.getPage(request);
  final int pageSize=PAGE_SIZE;
  final int windowSize=WINDOW_SIZE;
  final JSONObject requestJSONObject=new JSONObject();
  requestJSONObject.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,pageNum);
  requestJSONObject.put(Pagination.PAGINATION_PAGE_SIZE,pageSize);
  requestJSONObject.put(Pagination.PAGINATION_WINDOW_SIZE,windowSize);
  final List<String> fields=new ArrayList<>();
  fields.add(Keys.OBJECT_ID);
  fields.add(Breezemoon.BREEZEMOON_CONTENT);
  fields.add(Breezemoon.BREEZEMOON_CREATED);
  fields.add(Breezemoon.BREEZEMOON_AUTHOR_ID);
  fields.add(Breezemoon.BREEZEMOON_STATUS);
  final JSONObject result=breezemoonQueryService.getBreezemoons(requestJSONObject,fields);
  dataModel.put(Breezemoon.BREEZEMOONS,CollectionUtils.jsonArrayToList(result.optJSONArray(Breezemoon.BREEZEMOONS)));
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
