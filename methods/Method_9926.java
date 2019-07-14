/** 
 * Updates an article with the specified request json object.
 * @param requestJSONObject the specified request json object, for example,"oId": "", "articleTitle": "", "articleTags": "", "articleContent": "", "articleEditorType": "", "articleCommentable": boolean, // optional, default to true "articleType": int // optional, default to 0 "articleRewardContent": "", // optional, default to "" "articleRewardPoint": int, // optional, default to 0 "articleQnAOfferPoint": int, // optional, default to 0 "articleIP": "", // optional, default to "" "articleUA": "", // optional default to "" "articleNotifyFollowers": boolean
 * @throws ServiceException service exception
 */
public synchronized void updateArticle(final JSONObject requestJSONObject) throws ServiceException {
  String articleTitle=requestJSONObject.optString(Article.ARTICLE_TITLE);
  String articleId;
  JSONObject articleToUpdate;
  JSONObject oldArticle;
  String authorId;
  JSONObject author;
  int updatePointSum;
  int articleAnonymous;
  try {
    final JSONObject option=optionRepository.get(Option.ID_C_MISC_ALLOW_ADD_ARTICLE);
    if (!"0".equals(option.optString(Option.OPTION_VALUE))) {
      throw new ServiceException(langPropsService.get("notAllowAddArticleLabel"));
    }
    articleId=requestJSONObject.optString(Keys.OBJECT_ID);
    articleToUpdate=articleRepository.get(articleId);
    oldArticle=JSONs.clone(articleToUpdate);
    authorId=articleToUpdate.optString(Article.ARTICLE_AUTHOR_ID);
    author=userRepository.get(authorId);
    if (UserExt.USER_STATUS_C_VALID != author.optInt(UserExt.USER_STATUS)) {
      throw new ServiceException(langPropsService.get("userStatusInvalidLabel"));
    }
    final long followerCnt=followQueryService.getFollowerCount(authorId,Follow.FOLLOWING_TYPE_C_USER);
    int addition=(int)Math.round(Math.sqrt(followerCnt));
    final long collectCnt=followQueryService.getFollowerCount(articleId,Follow.FOLLOWING_TYPE_C_ARTICLE);
    final long watchCnt=followQueryService.getFollowerCount(articleId,Follow.FOLLOWING_TYPE_C_ARTICLE_WATCH);
    addition+=(collectCnt + watchCnt) * 2;
    updatePointSum=Pointtransfer.TRANSFER_SUM_C_UPDATE_ARTICLE + addition;
    articleAnonymous=articleToUpdate.optInt(Article.ARTICLE_ANONYMOUS);
    if (Article.ARTICLE_ANONYMOUS_C_PUBLIC == articleAnonymous) {
      final int balance=author.optInt(UserExt.USER_POINT);
      if (balance - updatePointSum < 0) {
        throw new ServiceException(langPropsService.get("insufficientBalanceLabel"));
      }
    }
    final JSONObject maybeExist=articleRepository.getByTitle(articleTitle);
    if (null != maybeExist) {
      if (!articleToUpdate.optString(Article.ARTICLE_TITLE).equals(articleTitle)) {
        final String existArticleAuthorId=maybeExist.optString(Article.ARTICLE_AUTHOR_ID);
        String msg;
        if (existArticleAuthorId.equals(authorId)) {
          msg=langPropsService.get("duplicatedArticleTitleSelfLabel");
          msg=msg.replace("{article}","<a target='_blank' href='/article/" + maybeExist.optString(Keys.OBJECT_ID) + "'>" + articleTitle + "</a>");
        }
 else {
          final JSONObject existArticleAuthor=userRepository.get(existArticleAuthorId);
          final String userName=existArticleAuthor.optString(User.USER_NAME);
          msg=langPropsService.get("duplicatedArticleTitleLabel");
          msg=msg.replace("{user}","<a target='_blank' href='/member/" + userName + "'>" + userName + "</a>");
          msg=msg.replace("{article}","<a target='_blank' href='/article/" + maybeExist.optString(Keys.OBJECT_ID) + "'>" + articleTitle + "</a>");
        }
        throw new ServiceException(msg);
      }
    }
  }
 catch (  final RepositoryException e) {
    throw new ServiceException(e);
  }
  final int qnaOfferPoint=requestJSONObject.optInt(Article.ARTICLE_QNA_OFFER_POINT,0);
  if (qnaOfferPoint < articleToUpdate.optInt(Article.ARTICLE_QNA_OFFER_POINT)) {
    throw new ServiceException(langPropsService.get("qnaOfferPointMustMoreThanOldLabel"));
  }
  articleToUpdate.put(Article.ARTICLE_QNA_OFFER_POINT,qnaOfferPoint);
  final int articleType=requestJSONObject.optInt(Article.ARTICLE_TYPE,Article.ARTICLE_TYPE_C_NORMAL);
  final Transaction transaction=articleRepository.beginTransaction();
  try {
    requestJSONObject.put(Article.ARTICLE_ANONYMOUS,articleAnonymous);
    processTagsForArticleUpdate(articleToUpdate,requestJSONObject,author);
    userRepository.update(author.optString(Keys.OBJECT_ID),author);
    articleTitle=Emotions.toAliases(articleTitle);
    articleTitle=Pangu.spacingText(articleTitle);
    final String oldTitle=articleToUpdate.optString(Article.ARTICLE_TITLE);
    articleToUpdate.put(Article.ARTICLE_TITLE,articleTitle);
    articleToUpdate.put(Article.ARTICLE_TAGS,requestJSONObject.optString(Article.ARTICLE_TAGS));
    articleToUpdate.put(Article.ARTICLE_COMMENTABLE,requestJSONObject.optBoolean(Article.ARTICLE_COMMENTABLE,true));
    articleToUpdate.put(Article.ARTICLE_TYPE,articleType);
    articleToUpdate.put(Article.ARTICLE_SHOW_IN_LIST,requestJSONObject.optInt(Article.ARTICLE_SHOW_IN_LIST,Article.ARTICLE_SHOW_IN_LIST_C_YES));
    String articleContent=requestJSONObject.optString(Article.ARTICLE_CONTENT);
    articleContent=Emotions.toAliases(articleContent);
    articleContent=articleContent.replace(langPropsService.get("uploadingLabel",Locale.SIMPLIFIED_CHINESE),"");
    articleContent=articleContent.replace(langPropsService.get("uploadingLabel",Locale.US),"");
    if (Article.ARTICLE_TYPE_C_THOUGHT != articleType) {
      articleContent=Runes.removeControlChars(articleContent);
    }
    final String oldContent=articleToUpdate.optString(Article.ARTICLE_CONTENT);
    articleToUpdate.put(Article.ARTICLE_CONTENT,articleContent);
    final long currentTimeMillis=System.currentTimeMillis();
    final long createTime=articleToUpdate.optLong(Keys.OBJECT_ID);
    final boolean notIn5m=currentTimeMillis - createTime > 1000 * 60 * 5;
    articleToUpdate.put(Article.ARTICLE_UPDATE_TIME,currentTimeMillis);
    final int rewardPoint=requestJSONObject.optInt(Article.ARTICLE_REWARD_POINT,0);
    boolean enableReward=false;
    if (0 < rewardPoint) {
      if (1 > articleToUpdate.optInt(Article.ARTICLE_REWARD_POINT)) {
        enableReward=true;
      }
      String rewardContent=requestJSONObject.optString(Article.ARTICLE_REWARD_CONTENT);
      rewardContent=Emotions.toAliases(rewardContent);
      rewardContent=Runes.removeControlChars(rewardContent);
      articleToUpdate.put(Article.ARTICLE_REWARD_CONTENT,rewardContent);
      articleToUpdate.put(Article.ARTICLE_REWARD_POINT,rewardPoint);
    }
    final String ip=requestJSONObject.optString(Article.ARTICLE_IP);
    articleToUpdate.put(Article.ARTICLE_IP,ip);
    String ua=requestJSONObject.optString(Article.ARTICLE_UA);
    if (StringUtils.length(ua) > Common.MAX_LENGTH_UA) {
      ua=StringUtils.substring(ua,0,Common.MAX_LENGTH_UA);
    }
    articleToUpdate.put(Article.ARTICLE_UA,ua);
    articleToUpdate.put(Article.ARTICLE_AUDIO_URL,"");
    articleRepository.update(articleId,articleToUpdate);
    final boolean titleChanged=!oldTitle.replaceAll("\\s+","").equals(articleTitle.replaceAll("\\s+",""));
    final boolean contentChanged=!oldContent.replaceAll("\\s+","").equals(articleContent.replaceAll("\\s+",""));
    if (notIn5m && Article.ARTICLE_TYPE_C_THOUGHT != articleType && (titleChanged || contentChanged)) {
      final JSONObject revision=new JSONObject();
      revision.put(Revision.REVISION_AUTHOR_ID,authorId);
      final JSONObject revisionData=new JSONObject();
      revisionData.put(Article.ARTICLE_TITLE,articleTitle);
      revisionData.put(Article.ARTICLE_CONTENT,articleContent);
      revision.put(Revision.REVISION_DATA,revisionData.toString());
      revision.put(Revision.REVISION_DATA_ID,articleId);
      revision.put(Revision.REVISION_DATA_TYPE,Revision.DATA_TYPE_C_ARTICLE);
      revisionRepository.add(revision);
    }
    transaction.commit();
    try {
      Thread.sleep(50);
    }
 catch (    final Exception e) {
    }
    if (Article.ARTICLE_ANONYMOUS_C_PUBLIC == articleAnonymous) {
      if (notIn5m) {
        pointtransferMgmtService.transfer(authorId,Pointtransfer.ID_C_SYS,Pointtransfer.TRANSFER_TYPE_C_UPDATE_ARTICLE,updatePointSum,articleId,System.currentTimeMillis(),"");
      }
      if (enableReward) {
        pointtransferMgmtService.transfer(authorId,Pointtransfer.ID_C_SYS,Pointtransfer.TRANSFER_TYPE_C_ADD_ARTICLE_REWARD,Pointtransfer.TRANSFER_SUM_C_ADD_ARTICLE_REWARD,articleId,System.currentTimeMillis(),"");
      }
    }
    final JSONObject eventData=new JSONObject();
    eventData.put(Article.ARTICLE,articleToUpdate);
    eventData.put(Common.OLD_ARTICLE,oldArticle);
    eventData.put(Article.ARTICLE_T_NOTIFY_FOLLOWERS,requestJSONObject.optBoolean(Article.ARTICLE_T_NOTIFY_FOLLOWERS));
    eventManager.fireEventAsynchronously(new Event<>(EventTypes.UPDATE_ARTICLE,eventData));
  }
 catch (  final Exception e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Updates an article failed",e);
    throw new ServiceException(e);
  }
}
