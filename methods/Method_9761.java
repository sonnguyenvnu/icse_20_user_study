/** 
 * Shows admin articles.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/articles",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showArticles(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/articles.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final int pageNum=Paginator.getPage(request);
  final int pageSize=PAGE_SIZE;
  final int windowSize=WINDOW_SIZE;
  final JSONObject requestJSONObject=new JSONObject();
  requestJSONObject.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,pageNum);
  requestJSONObject.put(Pagination.PAGINATION_PAGE_SIZE,pageSize);
  requestJSONObject.put(Pagination.PAGINATION_WINDOW_SIZE,windowSize);
  final String articleId=context.param("id");
  if (StringUtils.isNotBlank(articleId)) {
    requestJSONObject.put(Keys.OBJECT_ID,articleId);
  }
  final List<String> articleFields=new ArrayList<>();
  articleFields.add(Keys.OBJECT_ID);
  articleFields.add(Article.ARTICLE_TITLE);
  articleFields.add(Article.ARTICLE_PERMALINK);
  articleFields.add(Article.ARTICLE_CREATE_TIME);
  articleFields.add(Article.ARTICLE_VIEW_CNT);
  articleFields.add(Article.ARTICLE_COMMENT_CNT);
  articleFields.add(Article.ARTICLE_AUTHOR_ID);
  articleFields.add(Article.ARTICLE_TAGS);
  articleFields.add(Article.ARTICLE_STATUS);
  articleFields.add(Article.ARTICLE_STICK);
  final JSONObject result=articleQueryService.getArticles(requestJSONObject,articleFields);
  dataModel.put(Article.ARTICLES,CollectionUtils.jsonArrayToList(result.optJSONArray(Article.ARTICLES)));
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
