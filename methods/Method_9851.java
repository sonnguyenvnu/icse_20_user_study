/** 
 * Show tag articles.
 * @param context the specified context
 */
@RequestProcessing(value={"/tag/{tagURI}","/tag/{tagURI}/hot","/tag/{tagURI}/good","/tag/{tagURI}/reply","/tag/{tagURI}/perfect"},method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,AnonymousViewCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showTagArticles(final RequestContext context){
  final String tagURI=context.pathVar("tagURI");
  final HttpServletRequest request=context.getRequest();
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"tag-articles.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  dataModelService.fillHeaderAndFooter(context,dataModel);
  final int pageNum=Paginator.getPage(request);
  int pageSize=Symphonys.ARTICLE_LIST_CNT;
  final JSONObject user=Sessions.getUser();
  if (null != user) {
    pageSize=user.optInt(UserExt.USER_LIST_PAGE_SIZE);
    if (!UserExt.finshedGuide(user)) {
      context.sendRedirect(Latkes.getServePath() + "/guide");
      return;
    }
  }
  final JSONObject tag=tagQueryService.getTagByURI(tagURI);
  if (null == tag) {
    context.sendError(HttpServletResponse.SC_NOT_FOUND);
    return;
  }
  tag.put(Common.IS_RESERVED,tagQueryService.isReservedTag(tag.optString(Tag.TAG_TITLE)));
  dataModel.put(Tag.TAG,tag);
  final String tagId=tag.optString(Keys.OBJECT_ID);
  final List<JSONObject> relatedTags=tagQueryService.getRelatedTags(tagId,Symphonys.TAG_RELATED_TAGS_CNT);
  tag.put(Tag.TAG_T_RELATED_TAGS,(Object)relatedTags);
  final boolean isLoggedIn=(Boolean)dataModel.get(Common.IS_LOGGED_IN);
  if (isLoggedIn) {
    final JSONObject currentUser=Sessions.getUser();
    final String followerId=currentUser.optString(Keys.OBJECT_ID);
    final boolean isFollowing=followQueryService.isFollowing(followerId,tagId,Follow.FOLLOWING_TYPE_C_TAG);
    dataModel.put(Common.IS_FOLLOWING,isFollowing);
  }
  String sortModeStr=StringUtils.substringAfter(context.requestURI(),"/tag/" + tagURI);
  int sortMode;
switch (sortModeStr) {
case "":
    sortMode=0;
  break;
case "/hot":
sortMode=1;
break;
case "/good":
sortMode=2;
break;
case "/reply":
sortMode=3;
break;
case "/perfect":
sortMode=4;
break;
default :
sortMode=0;
}
final List<JSONObject> articles=articleQueryService.getArticlesByTag(sortMode,tag,pageNum,pageSize);
dataModel.put(Article.ARTICLES,articles);
final JSONObject tagCreator=tagQueryService.getCreator(tagId);
tag.put(Tag.TAG_T_CREATOR_THUMBNAIL_URL,tagCreator.optString(Tag.TAG_T_CREATOR_THUMBNAIL_URL));
tag.put(Tag.TAG_T_CREATOR_NAME,tagCreator.optString(Tag.TAG_T_CREATOR_NAME));
tag.put(Tag.TAG_T_PARTICIPANTS,(Object)tagQueryService.getParticipants(tagId,Symphonys.ARTICLE_LIST_PARTICIPANTS_CNT));
final int tagRefCnt=tag.getInt(Tag.TAG_REFERENCE_CNT);
final int pageCount=(int)Math.ceil(tagRefCnt / (double)pageSize);
final int windowSize=Symphonys.ARTICLE_LIST_WIN_SIZE;
final List<Integer> pageNums=Paginator.paginate(pageNum,pageSize,pageCount,windowSize);
if (!pageNums.isEmpty()) {
dataModel.put(Pagination.PAGINATION_FIRST_PAGE_NUM,pageNums.get(0));
dataModel.put(Pagination.PAGINATION_LAST_PAGE_NUM,pageNums.get(pageNums.size() - 1));
}
dataModel.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,pageNum);
dataModel.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
dataModel.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
dataModelService.fillRandomArticles(dataModel);
dataModelService.fillSideHotArticles(dataModel);
dataModelService.fillSideTags(dataModel);
dataModelService.fillLatestCmts(dataModel);
dataModel.put(Common.CURRENT,StringUtils.substringAfter(URLs.decode(context.requestURI()),"/tag/" + tagURI));
}
