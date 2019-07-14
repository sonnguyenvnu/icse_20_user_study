/** 
 * Organizes the specified article. <ul> <li>converts create/update/latest comment time (long) to date type and format string</li> <li>generates author thumbnail URL</li> <li>generates author name</li> <li>escapes article title &lt; and &gt;</li> <li>generates article heat</li> <li>generates article view count display format(1k+/1.5k+...)</li> <li>generates time ago text</li> <li>generates comment time ago text</li> <li>generates stick remains minutes</li> <li>anonymous process</li> <li>builds tag objects</li> <li>generates article preview content</li> <li>extracts the first image URL</li> <li>image processing if using Qiniu</li> </ul>
 * @param article the specified article
 * @throws RepositoryException repository exception
 */
public void organizeArticle(final JSONObject article) throws RepositoryException {
  article.put(Article.ARTICLE_T_ORIGINAL_CONTENT,article.optString(Article.ARTICLE_CONTENT));
  article.put(Common.OFFERED,false);
  toArticleDate(article);
  genArticleAuthor(article);
  final String previewContent=getArticleMetaDesc(article);
  article.put(Article.ARTICLE_T_PREVIEW_CONTENT,previewContent);
  article.put(Article.ARTICLE_T_THUMBNAIL_URL,getArticleThumbnail(article));
  final int articleType=article.optInt(Article.ARTICLE_TYPE);
  if (Article.ARTICLE_TYPE_C_THOUGHT != articleType) {
    String content=article.optString(Article.ARTICLE_CONTENT);
    content=Images.qiniuImgProcessing(content);
    article.put(Article.ARTICLE_CONTENT,content);
  }
  final String title=Escapes.escapeHTML(article.optString(Article.ARTICLE_TITLE));
  article.put(Article.ARTICLE_TITLE,title);
  article.put(Article.ARTICLE_T_TITLE_EMOJI,Emotions.convert(title));
  article.put(Article.ARTICLE_T_TITLE_EMOJI_UNICODE,EmojiParser.parseToUnicode(title));
  if (Article.ARTICLE_STATUS_C_INVALID == article.optInt(Article.ARTICLE_STATUS)) {
    article.put(Article.ARTICLE_TITLE,langPropsService.get("articleTitleBlockLabel"));
    article.put(Article.ARTICLE_T_TITLE_EMOJI,langPropsService.get("articleTitleBlockLabel"));
    article.put(Article.ARTICLE_T_TITLE_EMOJI_UNICODE,langPropsService.get("articleTitleBlockLabel"));
    article.put(Article.ARTICLE_CONTENT,langPropsService.get("articleContentBlockLabel"));
  }
  final String articleId=article.optString(Keys.OBJECT_ID);
  Integer viewingCnt=ArticleChannel.ARTICLE_VIEWS.get(articleId);
  if (null == viewingCnt) {
    viewingCnt=0;
  }
  article.put(Article.ARTICLE_T_HEAT,viewingCnt);
  final int viewCnt=article.optInt(Article.ARTICLE_VIEW_CNT);
  final double views=(double)viewCnt / 1000;
  if (views >= 1) {
    final DecimalFormat df=new DecimalFormat("#.#");
    article.put(Article.ARTICLE_T_VIEW_CNT_DISPLAY_FORMAT,df.format(views) + "K");
  }
  final long stick=article.optLong(Article.ARTICLE_STICK);
  long expired;
  if (stick > 0) {
    expired=stick + Symphonys.STICK_ARTICLE_TIME;
    final long remainsMills=Math.abs(System.currentTimeMillis() - expired);
    article.put(Article.ARTICLE_T_STICK_REMAINS,(int)Math.floor((double)remainsMills / 1000 / 60));
  }
 else {
    article.put(Article.ARTICLE_T_STICK_REMAINS,0);
  }
  String articleLatestCmterName=article.optString(Article.ARTICLE_LATEST_CMTER_NAME);
  if (StringUtils.isNotBlank(articleLatestCmterName) && UserRegisterValidation.invalidUserName(articleLatestCmterName)) {
    articleLatestCmterName=UserExt.ANONYMOUS_USER_NAME;
    article.put(Article.ARTICLE_LATEST_CMTER_NAME,articleLatestCmterName);
  }
  final String tagsStr=article.optString(Article.ARTICLE_TAGS);
  final List<JSONObject> tags=tagQueryService.buildTagObjs(tagsStr);
  article.put(Article.ARTICLE_T_TAG_OBJS,(Object)tags);
}
