/** 
 * Shows watch articles or users.
 * @param context the specified context
 */
@RequestProcessing(value={"/watch","/watch/users"},method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,AnonymousViewCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showWatch(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"watch.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  int pageSize=Symphonys.ARTICLE_LIST_CNT;
  final JSONObject user=Sessions.getUser();
  if (null != user) {
    pageSize=user.optInt(UserExt.USER_LIST_PAGE_SIZE);
    if (!UserExt.finshedGuide(user)) {
      context.sendRedirect(Latkes.getServePath() + "/guide");
      return;
    }
  }
  dataModel.put(Common.WATCHING_ARTICLES,Collections.emptyList());
  String sortModeStr=StringUtils.substringAfter(context.requestURI(),"/watch");
switch (sortModeStr) {
case "":
    if (null != user) {
      final List<JSONObject> followingTagArticles=articleQueryService.getFollowingTagArticles(user.optString(Keys.OBJECT_ID),1,pageSize);
      dataModel.put(Common.WATCHING_ARTICLES,followingTagArticles);
    }
  break;
case "/users":
if (null != user) {
  final List<JSONObject> followingUserArticles=articleQueryService.getFollowingUserArticles(user.optString(Keys.OBJECT_ID),1,pageSize);
  dataModel.put(Common.WATCHING_ARTICLES,followingUserArticles);
}
break;
}
dataModelService.fillHeaderAndFooter(context,dataModel);
dataModelService.fillRandomArticles(dataModel);
dataModelService.fillSideHotArticles(dataModel);
dataModelService.fillSideTags(dataModel);
dataModelService.fillLatestCmts(dataModel);
dataModel.put(Common.SELECTED,Common.WATCH);
dataModel.put(Common.CURRENT,StringUtils.substringAfter(context.requestURI(),"/watch"));
}
