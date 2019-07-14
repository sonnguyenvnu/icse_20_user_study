/** 
 * Gets preview content of the article specified with the given article id.
 * @param articleId the given article id
 * @param context   the specified request context
 * @return preview content
 */
public String getArticlePreviewContent(final String articleId,final RequestContext context){
  final JSONObject article=getArticle(articleId);
  if (null == article) {
    return null;
  }
  final int articleType=article.optInt(Article.ARTICLE_TYPE);
  if (Article.ARTICLE_TYPE_C_THOUGHT == articleType) {
    return null;
  }
  Stopwatchs.start("Get preview content");
  try {
    final int length=Integer.valueOf("150");
    String ret=article.optString(Article.ARTICLE_CONTENT);
    final String authorId=article.optString(Article.ARTICLE_AUTHOR_ID);
    final JSONObject author=userQueryService.getUser(authorId);
    if (null != author && UserExt.USER_STATUS_C_INVALID == author.optInt(UserExt.USER_STATUS) || Article.ARTICLE_STATUS_C_INVALID == article.optInt(Article.ARTICLE_STATUS)) {
      return Jsoup.clean(langPropsService.get("articleContentBlockLabel"),Whitelist.none());
    }
    final Set<String> userNames=userQueryService.getUserNames(ret);
    final JSONObject currentUser=Sessions.getUser();
    final String currentUserName=null == currentUser ? "" : currentUser.optString(User.USER_NAME);
    final String authorName=author.optString(User.USER_NAME);
    if (Article.ARTICLE_TYPE_C_DISCUSSION == articleType && !authorName.equals(currentUserName)) {
      boolean invited=false;
      for (      final String userName : userNames) {
        if (userName.equals(currentUserName)) {
          invited=true;
          break;
        }
      }
      if (!invited) {
        String blockContent=langPropsService.get("articleDiscussionLabel");
        blockContent=blockContent.replace("{user}",UserExt.getUserLink(authorName));
        return blockContent;
      }
    }
    ret=Emotions.convert(ret);
    ret=Markdowns.toHTML(ret);
    ret=Jsoup.clean(ret,Whitelist.none());
    if (ret.length() >= length) {
      ret=StringUtils.substring(ret,0,length) + " ....";
    }
    return ret;
  }
  finally {
    Stopwatchs.end();
  }
}
