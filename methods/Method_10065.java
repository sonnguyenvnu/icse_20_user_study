/** 
 * Gets 'at' type notifications with the specified user id, current page number and page size.
 * @param userId         the specified user id
 * @param currentPageNum the specified page number
 * @param pageSize       the specified page size
 * @return result json object, for example,      <pre>{ "paginationRecordCount": int, "rslts": java.util.List[{ "oId": "", // notification record id "authorName": "", "content": "", "thumbnailURL": "", "articleTitle": "", "articleType": int, "url": "", "createTime": java.util.Date, "hasRead": boolean, "atInArticle": boolean, "isAt": boolean, "articleTags": "", // if atInArticle is true "articleTagObjs": [{}, ....], // if atInArticle is true "articleCommentCnt": int // if atInArticle is true }, ....] } </pre>
 */
public JSONObject getAtNotifications(final String userId,final int currentPageNum,final int pageSize){
  final JSONObject ret=new JSONObject();
  final List<JSONObject> rslts=new ArrayList<>();
  ret.put(Keys.RESULTS,(Object)rslts);
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Notification.NOTIFICATION_USER_ID,FilterOperator.EQUAL,userId));
  final List<Filter> subFilters=new ArrayList<>();
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_AT));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_ARTICLE_NEW_FOLLOWER));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_ARTICLE_NEW_WATCHER));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_COMMENT_VOTE_UP));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_COMMENT_VOTE_DOWN));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_ARTICLE_VOTE_UP));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_ARTICLE_VOTE_DOWN));
  filters.add(new CompositeFilter(CompositeFilterOperator.OR,subFilters));
  final Query query=new Query().setPage(currentPageNum,pageSize).setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters)).addSort(Notification.NOTIFICATION_HAS_READ,SortDirection.ASCENDING).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING);
  try {
    final JSONObject queryResult=notificationRepository.get(query);
    final JSONArray results=queryResult.optJSONArray(Keys.RESULTS);
    ret.put(Pagination.PAGINATION_RECORD_COUNT,queryResult.optJSONObject(Pagination.PAGINATION).optInt(Pagination.PAGINATION_RECORD_COUNT));
    for (int i=0; i < results.length(); i++) {
      final JSONObject notification=results.optJSONObject(i);
      final int dataType=notification.optInt(Notification.NOTIFICATION_DATA_TYPE);
      final String dataId=notification.optString(Notification.NOTIFICATION_DATA_ID);
      final JSONObject atNotification=new JSONObject();
      atNotification.put(Notification.NOTIFICATION_DATA_TYPE,dataType);
      String description="";
      atNotification.put(Keys.OBJECT_ID,notification.optString(Keys.OBJECT_ID));
      atNotification.put(Notification.NOTIFICATION_HAS_READ,notification.optBoolean(Notification.NOTIFICATION_HAS_READ));
      atNotification.put(Common.CREATE_TIME,new Date(notification.optLong(Keys.OBJECT_ID)));
switch (dataType) {
case Notification.DATA_TYPE_C_AT:
        final JSONObject comment=commentQueryService.getCommentById(dataId);
      if (null != comment) {
        final Query q=new Query().setPageCount(1).select(Article.ARTICLE_PERFECT,Article.ARTICLE_TITLE,Article.ARTICLE_TYPE).setFilter(new PropertyFilter(Keys.OBJECT_ID,FilterOperator.EQUAL,comment.optString(Comment.COMMENT_ON_ARTICLE_ID)));
        final JSONArray rlts=articleRepository.get(q).optJSONArray(Keys.RESULTS);
        final JSONObject article=rlts.optJSONObject(0);
        final String articleTitle=article.optString(Article.ARTICLE_TITLE);
        final int articleType=article.optInt(Article.ARTICLE_TYPE);
        final int articlePerfect=article.optInt(Article.ARTICLE_PERFECT);
        atNotification.put(Common.AUTHOR_NAME,comment.optString(Comment.COMMENT_T_AUTHOR_NAME));
        atNotification.put(Common.CONTENT,comment.optString(Comment.COMMENT_CONTENT));
        atNotification.put(Common.THUMBNAIL_URL,comment.optString(Comment.COMMENT_T_AUTHOR_THUMBNAIL_URL));
        atNotification.put(Article.ARTICLE_TITLE,Emotions.convert(articleTitle));
        atNotification.put(Article.ARTICLE_TYPE,articleType);
        atNotification.put(Common.URL,comment.optString(Comment.COMMENT_SHARP_URL));
        atNotification.put(Common.CREATE_TIME,comment.opt(Comment.COMMENT_CREATE_TIME));
        atNotification.put(Notification.NOTIFICATION_T_AT_IN_ARTICLE,false);
        atNotification.put(Article.ARTICLE_PERFECT,articlePerfect);
        atNotification.put(Article.ARTICLE_T_ID,comment.optString(Comment.COMMENT_ON_ARTICLE_ID));
        atNotification.put(Comment.COMMENT_T_ID,comment.optString(Keys.OBJECT_ID));
        rslts.add(atNotification);
      }
 else {
        final JSONObject article=articleRepository.get(dataId);
        final String articleAuthorId=article.optString(Article.ARTICLE_AUTHOR_ID);
        final JSONObject articleAuthor=userRepository.get(articleAuthorId);
        atNotification.put(Common.AUTHOR_NAME,articleAuthor.optString(User.USER_NAME));
        atNotification.put(Common.CONTENT,"");
        final String thumbnailURL=avatarQueryService.getAvatarURLByUser(articleAuthor,"48");
        atNotification.put(Common.THUMBNAIL_URL,thumbnailURL);
        atNotification.put(Article.ARTICLE_TITLE,Emotions.convert(article.optString(Article.ARTICLE_TITLE)));
        atNotification.put(Article.ARTICLE_TYPE,article.optInt(Article.ARTICLE_TYPE));
        atNotification.put(Common.URL,Latkes.getServePath() + article.optString(Article.ARTICLE_PERMALINK));
        atNotification.put(Common.CREATE_TIME,new Date(article.optLong(Article.ARTICLE_CREATE_TIME)));
        atNotification.put(Notification.NOTIFICATION_T_AT_IN_ARTICLE,true);
        final String tagsStr=article.optString(Article.ARTICLE_TAGS);
        atNotification.put(Article.ARTICLE_TAGS,tagsStr);
        final List<JSONObject> tags=tagQueryService.buildTagObjs(tagsStr);
        atNotification.put(Article.ARTICLE_T_TAG_OBJS,(Object)tags);
        atNotification.put(Article.ARTICLE_COMMENT_CNT,article.optInt(Article.ARTICLE_COMMENT_CNT));
        atNotification.put(Article.ARTICLE_PERFECT,article.optInt(Article.ARTICLE_PERFECT));
        atNotification.put(Article.ARTICLE_T_ID,article.optString(Keys.OBJECT_ID));
        rslts.add(atNotification);
      }
    break;
case Notification.DATA_TYPE_C_ARTICLE_NEW_FOLLOWER:
case Notification.DATA_TYPE_C_ARTICLE_NEW_WATCHER:
  final String articleId=dataId.split("-")[0];
final String followerUserId=dataId.split("-")[1];
final JSONObject article=articleRepository.get(articleId);
if (null == article) {
description=langPropsService.get("removedLabel");
atNotification.put(Common.DESCRIPTION,description);
rslts.add(atNotification);
continue;
}
if (Notification.DATA_TYPE_C_ARTICLE_NEW_FOLLOWER == dataType) {
description=langPropsService.get("notificationArticleNewFollowerLabel");
}
 else if (Notification.DATA_TYPE_C_ARTICLE_NEW_WATCHER == dataType) {
description=langPropsService.get("notificationArticleNewWatcherLabel");
}
final JSONObject followerUser=userRepository.get(followerUserId);
final String followerUserName=followerUser.optString(User.USER_NAME);
atNotification.put(User.USER_NAME,followerUserName);
final String thumbnailURL=avatarQueryService.getAvatarURLByUser(followerUser,"48");
atNotification.put(Common.THUMBNAIL_URL,thumbnailURL);
final String userLink=UserExt.getUserLink(followerUserName);
description=description.replace("{user}",userLink);
final String articleLink=" <a href=\"" + Latkes.getServePath() + article.optString(Article.ARTICLE_PERMALINK) + "\">" + Emotions.convert(article.optString(Article.ARTICLE_TITLE)) + "</a>";
description=description.replace("{article}",articleLink);
atNotification.put(Common.DESCRIPTION,description);
rslts.add(atNotification);
break;
case Notification.DATA_TYPE_C_COMMENT_VOTE_UP:
case Notification.DATA_TYPE_C_COMMENT_VOTE_DOWN:
final JSONObject user=userRepository.get(userId);
final int cmtViewMode=user.optInt(UserExt.USER_COMMENT_VIEW_MODE);
final String commentId=dataId.split("-")[0];
final String cmtVoterId=dataId.split("-")[1];
final JSONObject cmtVoter=userRepository.get(cmtVoterId);
String voterUserName=cmtVoter.optString(User.USER_NAME);
atNotification.put(User.USER_NAME,voterUserName);
String thumbnailURLVote=avatarQueryService.getAvatarURLByUser(cmtVoter,"48");
atNotification.put(Common.THUMBNAIL_URL,thumbnailURLVote);
JSONObject articleVote=null;
if (Notification.DATA_TYPE_C_COMMENT_VOTE_UP == dataType) {
description=langPropsService.get("notificationCommentVoteUpLabel");
articleVote=commentRepository.get(commentId);
if (null == articleVote) {
description=langPropsService.get("removedLabel");
atNotification.put(Common.DESCRIPTION,description);
rslts.add(atNotification);
continue;
}
articleVote=articleRepository.get(articleVote.optString(Comment.COMMENT_ON_ARTICLE_ID));
}
 else if (Notification.DATA_TYPE_C_COMMENT_VOTE_DOWN == dataType) {
description=langPropsService.get("notificationCommentVoteDownLabel");
articleVote=commentRepository.get(commentId);
if (null == articleVote) {
description=langPropsService.get("removedLabel");
atNotification.put(Common.DESCRIPTION,description);
rslts.add(atNotification);
continue;
}
articleVote=articleRepository.get(articleVote.optString(Comment.COMMENT_ON_ARTICLE_ID));
}
if (null == articleVote) {
description=langPropsService.get("removedLabel");
atNotification.put(Common.DESCRIPTION,description);
rslts.add(atNotification);
continue;
}
String userLinkVote=UserExt.getUserLink(voterUserName);
description=description.replace("{user}",userLinkVote);
final String cmtVoteURL=commentQueryService.getCommentURL(commentId,cmtViewMode,Symphonys.ARTICLE_COMMENTS_CNT);
atNotification.put(Common.DESCRIPTION,description.replace("{article}",Emotions.convert(cmtVoteURL)));
rslts.add(atNotification);
break;
case Notification.DATA_TYPE_C_ARTICLE_VOTE_UP:
case Notification.DATA_TYPE_C_ARTICLE_VOTE_DOWN:
final String voteArticleId=dataId.split("-")[0];
final String voterId=dataId.split("-")[1];
final JSONObject voter=userRepository.get(voterId);
voterUserName=voter.optString(User.USER_NAME);
atNotification.put(User.USER_NAME,voterUserName);
thumbnailURLVote=avatarQueryService.getAvatarURLByUser(voter,"48");
atNotification.put(Common.THUMBNAIL_URL,thumbnailURLVote);
JSONObject voteArticle=null;
if (Notification.DATA_TYPE_C_ARTICLE_VOTE_UP == dataType) {
description=langPropsService.get("notificationArticleVoteUpLabel");
voteArticle=articleRepository.get(voteArticleId);
}
 else if (Notification.DATA_TYPE_C_ARTICLE_VOTE_DOWN == dataType) {
description=langPropsService.get("notificationArticleVoteDownLabel");
voteArticle=articleRepository.get(voteArticleId);
}
if (null == voteArticle) {
description=langPropsService.get("removedLabel");
atNotification.put(Common.DESCRIPTION,description);
rslts.add(atNotification);
continue;
}
userLinkVote=UserExt.getUserLink(voterUserName);
description=description.replace("{user}",userLinkVote);
final String articleLinkVote=" <a href=\"" + Latkes.getServePath() + voteArticle.optString(Article.ARTICLE_PERMALINK) + "\">" + Emotions.convert(voteArticle.optString(Article.ARTICLE_TITLE)) + "</a>";
description=description.replace("{article}",articleLinkVote);
atNotification.put(Common.DESCRIPTION,description);
rslts.add(atNotification);
break;
}
}
return ret;
}
 catch (final RepositoryException e) {
LOGGER.log(Level.ERROR,"Gets [at] notifications",e);
return null;
}
}
