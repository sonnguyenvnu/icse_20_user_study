/** 
 * Adds an article with the specified request json object. <p> <b>Note</b>: This method just for admin console. </p>
 * @param requestJSONObject the specified request json object, for example,"articleTitle": "", "articleTags": "", "articleContent": "", "articleRewardContent": "", "articleRewardPoint": int, "userName": "", "time": long , see  {@link Article} for more details
 * @return generated article id
 * @throws ServiceException service exception
 */
public synchronized String addArticleByAdmin(final JSONObject requestJSONObject) throws ServiceException {
  JSONObject author;
  try {
    author=userRepository.getByName(requestJSONObject.optString(User.USER_NAME));
    if (null == author) {
      throw new ServiceException(langPropsService.get("notFoundUserLabel"));
    }
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.DEBUG,"Admin adds article failed",e);
    throw new ServiceException(e.getMessage());
  }
  final Transaction transaction=articleRepository.beginTransaction();
  try {
    final long time=requestJSONObject.optLong(Common.TIME);
    final String ret=String.valueOf(time);
    final JSONObject article=new JSONObject();
    article.put(Keys.OBJECT_ID,ret);
    article.put(Article.ARTICLE_AUTHOR_ID,author.optString(Keys.OBJECT_ID));
    article.put(Article.ARTICLE_TITLE,Emotions.toAliases(requestJSONObject.optString(Article.ARTICLE_TITLE)));
    article.put(Article.ARTICLE_CONTENT,Emotions.toAliases(requestJSONObject.optString(Article.ARTICLE_CONTENT)));
    article.put(Article.ARTICLE_REWARD_CONTENT,requestJSONObject.optString(Article.ARTICLE_REWARD_CONTENT));
    article.put(Article.ARTICLE_EDITOR_TYPE,0);
    article.put(Article.ARTICLE_COMMENT_CNT,0);
    article.put(Article.ARTICLE_VIEW_CNT,0);
    article.put(Article.ARTICLE_THANK_CNT,0);
    article.put(Article.ARTICLE_GOOD_CNT,0);
    article.put(Article.ARTICLE_BAD_CNT,0);
    article.put(Article.ARTICLE_COLLECT_CNT,0);
    article.put(Article.ARTICLE_WATCH_CNT,0);
    article.put(Article.ARTICLE_COMMENTABLE,true);
    article.put(Article.ARTICLE_CREATE_TIME,time);
    article.put(Article.ARTICLE_UPDATE_TIME,time);
    article.put(Article.ARTICLE_LATEST_CMT_TIME,ret);
    article.put(Article.ARTICLE_LATEST_CMTER_NAME,"");
    article.put(Article.ARTICLE_PERMALINK,"/article/" + ret);
    article.put(Article.ARTICLE_RANDOM_DOUBLE,Math.random());
    article.put(Article.REDDIT_SCORE,0);
    article.put(Article.ARTICLE_STATUS,Article.ARTICLE_STATUS_C_VALID);
    article.put(Article.ARTICLE_TYPE,Article.ARTICLE_TYPE_C_NORMAL);
    article.put(Article.ARTICLE_REWARD_POINT,requestJSONObject.optInt(Article.ARTICLE_REWARD_POINT));
    article.put(Article.ARTICLE_QNA_OFFER_POINT,0);
    article.put(Article.ARTICLE_PUSH_ORDER,0);
    article.put(Article.ARTICLE_IMG1_URL,"");
    article.put(Article.ARTICLE_CITY,"");
    String articleTags=requestJSONObject.optString(Article.ARTICLE_TAGS);
    articleTags=Tag.formatTags(articleTags);
    if (StringUtils.containsIgnoreCase(articleTags,Tag.TAG_TITLE_C_SANDBOX)) {
      articleTags=Tag.TAG_TITLE_C_SANDBOX;
    }
    if (StringUtils.isBlank(articleTags)) {
      articleTags="???";
    }
    articleTags=Tag.formatTags(articleTags);
    article.put(Article.ARTICLE_TAGS,articleTags);
    String[] tagTitles=articleTags.split(",");
    tag(tagTitles,article,author);
    final String ip=requestJSONObject.optString(Article.ARTICLE_IP);
    article.put(Article.ARTICLE_IP,ip);
    String ua=requestJSONObject.optString(Article.ARTICLE_UA);
    if (StringUtils.length(ua) > Common.MAX_LENGTH_UA) {
      ua=StringUtils.substring(ua,0,Common.MAX_LENGTH_UA);
    }
    article.put(Article.ARTICLE_UA,ua);
    article.put(Article.ARTICLE_STICK,0L);
    article.put(Article.ARTICLE_ANONYMOUS,Article.ARTICLE_ANONYMOUS_C_PUBLIC);
    article.put(Article.ARTICLE_PERFECT,Article.ARTICLE_PERFECT_C_NOT_PERFECT);
    article.put(Article.ARTICLE_ANONYMOUS_VIEW,Article.ARTICLE_ANONYMOUS_VIEW_C_USE_GLOBAL);
    article.put(Article.ARTICLE_AUDIO_URL,"");
    article.put(Article.ARTICLE_SHOW_IN_LIST,requestJSONObject.optInt(Article.ARTICLE_SHOW_IN_LIST,Article.ARTICLE_SHOW_IN_LIST_C_YES));
    final JSONObject articleCntOption=optionRepository.get(Option.ID_C_STATISTIC_ARTICLE_COUNT);
    final int articleCnt=articleCntOption.optInt(Option.OPTION_VALUE);
    articleCntOption.put(Option.OPTION_VALUE,articleCnt + 1);
    optionRepository.update(Option.ID_C_STATISTIC_ARTICLE_COUNT,articleCntOption);
    author.put(UserExt.USER_ARTICLE_COUNT,author.optInt(UserExt.USER_ARTICLE_COUNT) + 1);
    author.put(UserExt.USER_LATEST_ARTICLE_TIME,time);
    userRepository.update(author.optString(Keys.OBJECT_ID),author);
    articleRepository.add(article);
    transaction.commit();
    tagMgmtService.relateTags(article.optString(Article.ARTICLE_TAGS));
    final JSONObject eventData=new JSONObject();
    eventData.put(Article.ARTICLE,article);
    eventManager.fireEventAsynchronously(new Event<>(EventTypes.ADD_ARTICLE,eventData));
    return ret;
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Admin adds an article failed",e);
    throw new ServiceException(e.getMessage());
  }
}
