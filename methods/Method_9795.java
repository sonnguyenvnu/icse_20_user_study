/** 
 * Notifies the specified comment message to browsers.
 * @param message the specified message
 */
public static void notifyComment(final JSONObject message){
  message.put(Common.TYPE,Comment.COMMENT);
  final BeanManager beanManager=BeanManager.getInstance();
  final UserQueryService userQueryService=beanManager.getReference(UserQueryService.class);
  final RoleQueryService roleQueryService=beanManager.getReference(RoleQueryService.class);
  final LangPropsService langPropsService=beanManager.getReference(LangPropsService.class);
  final JSONObject article=message.optJSONObject(Article.ARTICLE);
  for (  final Session session : SESSIONS) {
    final String viewingArticleId=Channels.getHttpParameter(session,Article.ARTICLE_T_ID);
    if (StringUtils.isBlank(viewingArticleId) || !viewingArticleId.equals(message.optString(Article.ARTICLE_T_ID))) {
      continue;
    }
    final int articleType=Integer.valueOf(Channels.getHttpParameter(session,Article.ARTICLE_TYPE));
    final JSONObject user=(JSONObject)Channels.getHttpSessionAttribute(session,User.USER);
    final boolean isLoggedIn=null != user;
    try {
      if (Article.ARTICLE_TYPE_C_DISCUSSION == articleType) {
        if (!isLoggedIn) {
          continue;
        }
        final String userName=user.optString(User.USER_NAME);
        final String userRole=user.optString(User.USER_ROLE);
        final String articleAuthorId=article.optString(Article.ARTICLE_AUTHOR_ID);
        final String userId=user.optString(Keys.OBJECT_ID);
        if (!userId.equals(articleAuthorId)) {
          final String articleContent=article.optString(Article.ARTICLE_CONTENT);
          final Set<String> userNames=userQueryService.getUserNames(articleContent);
          boolean invited=false;
          for (          final String inviteUserName : userNames) {
            if (inviteUserName.equals(userName)) {
              invited=true;
              break;
            }
          }
          if (Role.ROLE_ID_C_ADMIN.equals(userRole)) {
            invited=true;
          }
          if (!invited) {
            continue;
          }
        }
      }
      message.put(Comment.COMMENT_T_NICE,false);
      message.put(Common.REWARED_COUNT,0);
      message.put(Comment.COMMENT_T_VOTE,-1);
      message.put(Common.REWARDED,false);
      message.put(Comment.COMMENT_REVISION_COUNT,1);
      final Map dataModel=new HashMap();
      dataModel.put(Common.IS_LOGGED_IN,isLoggedIn);
      dataModel.put(Common.CURRENT_USER,user);
      article.put(Common.OFFERED,false);
      dataModel.put(Article.ARTICLE,article);
      dataModel.put(Common.CSRF_TOKEN,Channels.getHttpSessionAttribute(session,Common.CSRF_TOKEN));
      Keys.fillServer(dataModel);
      dataModel.put(Comment.COMMENT,message);
      if (isLoggedIn) {
        dataModel.putAll(langPropsService.getAll(Locales.getLocale(user.optString(UserExt.USER_LANGUAGE))));
        final String userId=user.optString(Keys.OBJECT_ID);
        final Map<String,JSONObject> permissions=roleQueryService.getUserPermissionsGrantMap(userId);
        dataModel.put(Permission.PERMISSIONS,permissions);
      }
 else {
        dataModel.putAll(langPropsService.getAll(Locales.getLocale()));
        final Map<String,JSONObject> permissions=roleQueryService.getPermissionsGrantMap(Role.ROLE_ID_C_VISITOR);
        dataModel.put(Permission.PERMISSIONS,permissions);
      }
      final String templateDirName=(String)session.getUserProperties().get(Keys.TEMAPLTE_DIR_NAME);
      final Template template=Templates.getTemplate(templateDirName + "/common/comment.ftl");
      final StringWriter stringWriter=new StringWriter();
      template.process(dataModel,stringWriter);
      stringWriter.close();
      message.put("cmtTpl",stringWriter.toString());
      final String msgStr=message.toString();
      if (session.isOpen()) {
        session.getAsyncRemote().sendText(msgStr);
      }
    }
 catch (    final Exception e) {
      LOGGER.log(Level.ERROR,"Notify comment error",e);
    }
  }
}
