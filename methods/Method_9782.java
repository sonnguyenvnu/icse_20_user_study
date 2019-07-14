/** 
 * Shows add article.
 * @param context the specified context
 */
@RequestProcessing(value="/post",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,LoginCheck.class}) @After({CSRFToken.class,PermissionGrant.class,StopwatchEndAdvice.class}) public void showAddArticle(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"home/post.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final long imgMaxSize=Symphonys.UPLOAD_IMG_MAX;
  dataModel.put("imgMaxSize",imgMaxSize);
  final long fileMaxSize=Symphonys.UPLOAD_FILE_MAX;
  dataModel.put("fileMaxSize",fileMaxSize);
  String tags=context.param(Tag.TAGS);
  final JSONObject currentUser=Sessions.getUser();
  if (StringUtils.isBlank(tags)) {
    tags="";
    dataModel.put(Tag.TAGS,tags);
  }
 else {
    tags=Tag.formatTags(tags);
    final String[] tagTitles=tags.split(",");
    final StringBuilder tagBuilder=new StringBuilder();
    for (    final String title : tagTitles) {
      final String tagTitle=title.trim();
      if (StringUtils.isBlank(tagTitle)) {
        continue;
      }
      if (Tag.containsWhiteListTags(tagTitle)) {
        tagBuilder.append(tagTitle).append(",");
        continue;
      }
      if (!Tag.TAG_TITLE_PATTERN.matcher(tagTitle).matches()) {
        continue;
      }
      if (tagTitle.length() > Tag.MAX_TAG_TITLE_LENGTH) {
        continue;
      }
      if (!Role.ROLE_ID_C_ADMIN.equals(currentUser.optString(User.USER_ROLE)) && ArrayUtils.contains(Symphonys.RESERVED_TAGS,tagTitle)) {
        continue;
      }
      tagBuilder.append(tagTitle).append(",");
    }
    if (tagBuilder.length() > 0) {
      tagBuilder.deleteCharAt(tagBuilder.length() - 1);
    }
    dataModel.put(Tag.TAGS,tagBuilder.toString());
  }
  final String type=context.param(Common.TYPE);
  if (StringUtils.isBlank(type)) {
    dataModel.put(Article.ARTICLE_TYPE,Article.ARTICLE_TYPE_C_NORMAL);
  }
 else {
    int articleType=Article.ARTICLE_TYPE_C_NORMAL;
    try {
      articleType=Integer.valueOf(type);
    }
 catch (    final Exception e) {
      LOGGER.log(Level.WARN,"Gets article type error [" + type + "]",e);
    }
    if (Article.isInvalidArticleType(articleType)) {
      articleType=Article.ARTICLE_TYPE_C_NORMAL;
    }
    dataModel.put(Article.ARTICLE_TYPE,articleType);
  }
  String at=context.param(Common.AT);
  at=StringUtils.trim(at);
  if (StringUtils.isNotBlank(at)) {
    dataModel.put(Common.AT,at + " ");
  }
  dataModelService.fillHeaderAndFooter(context,dataModel);
  String rewardEditorPlaceholderLabel=langPropsService.get("rewardEditorPlaceholderLabel");
  rewardEditorPlaceholderLabel=rewardEditorPlaceholderLabel.replace("{point}",String.valueOf(Pointtransfer.TRANSFER_SUM_C_ADD_ARTICLE_REWARD));
  dataModel.put("rewardEditorPlaceholderLabel",rewardEditorPlaceholderLabel);
  dataModel.put(Common.BROADCAST_POINT,Pointtransfer.TRANSFER_SUM_C_ADD_ARTICLE_BROADCAST);
  String articleContentErrorLabel=langPropsService.get("articleContentErrorLabel");
  articleContentErrorLabel=articleContentErrorLabel.replace("{maxArticleContentLength}",String.valueOf(ArticleAddValidation.MAX_ARTICLE_CONTENT_LENGTH));
  dataModel.put("articleContentErrorLabel",articleContentErrorLabel);
  fillPostArticleRequisite(dataModel,currentUser);
  fillDomainsWithTags(dataModel);
}
