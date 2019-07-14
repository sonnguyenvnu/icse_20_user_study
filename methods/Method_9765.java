/** 
 * Shows admin tags.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/tags",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showTags(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/tags.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final int pageNum=Paginator.getPage(request);
  final int pageSize=PAGE_SIZE;
  final int windowSize=WINDOW_SIZE;
  final JSONObject requestJSONObject=new JSONObject();
  requestJSONObject.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,pageNum);
  requestJSONObject.put(Pagination.PAGINATION_PAGE_SIZE,pageSize);
  requestJSONObject.put(Pagination.PAGINATION_WINDOW_SIZE,windowSize);
  final String tagTitle=context.param(Common.TITLE);
  if (StringUtils.isNotBlank(tagTitle)) {
    requestJSONObject.put(Tag.TAG_TITLE,tagTitle);
  }
  final List<String> tagFields=new ArrayList<>();
  tagFields.add(Keys.OBJECT_ID);
  tagFields.add(Tag.TAG_TITLE);
  tagFields.add(Tag.TAG_DESCRIPTION);
  tagFields.add(Tag.TAG_ICON_PATH);
  tagFields.add(Tag.TAG_COMMENT_CNT);
  tagFields.add(Tag.TAG_REFERENCE_CNT);
  tagFields.add(Tag.TAG_FOLLOWER_CNT);
  tagFields.add(Tag.TAG_STATUS);
  tagFields.add(Tag.TAG_GOOD_CNT);
  tagFields.add(Tag.TAG_BAD_CNT);
  tagFields.add(Tag.TAG_URI);
  tagFields.add(Tag.TAG_CSS);
  final JSONObject result=tagQueryService.getTags(requestJSONObject,tagFields);
  dataModel.put(Tag.TAGS,CollectionUtils.jsonArrayToList(result.optJSONArray(Tag.TAGS)));
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
