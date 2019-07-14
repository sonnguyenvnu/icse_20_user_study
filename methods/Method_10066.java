/** 
 * Gets 'following' type notifications with the specified user id, current page number and page size.
 * @param userId         the specified user id
 * @param currentPageNum the specified page number
 * @param pageSize       the specified page size
 * @return result json object, for example,      <pre>{ "paginationRecordCount": int, "rslts": java.util.List[{ "oId": "", // notification record id "authorName": "", "content": "", "thumbnailURL": "", "articleTitle": "", "articleType": int, "url": "", "createTime": java.util.Date, "hasRead": boolean, "type": "", // article/comment "articleTags": "", // if atInArticle is true "articleTagObjs": [{}, ....], // if atInArticle is true "articleCommentCnt": int // if atInArticle is true }, ....] } </pre>
 */
public JSONObject getFollowingNotifications(final String userId,final int currentPageNum,final int pageSize){
  final JSONObject ret=new JSONObject();
  final List<JSONObject> rslts=new ArrayList<>();
  ret.put(Keys.RESULTS,(Object)rslts);
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Notification.NOTIFICATION_USER_ID,FilterOperator.EQUAL,userId));
  final List<Filter> subFilters=new ArrayList<>();
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_FOLLOWING_USER));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_FOLLOWING_ARTICLE_UPDATE));
  subFilters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_FOLLOWING_ARTICLE_COMMENT));
  filters.add(new CompositeFilter(CompositeFilterOperator.OR,subFilters));
  final Query query=new Query().setPage(currentPageNum,pageSize).setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters)).addSort(Notification.NOTIFICATION_HAS_READ,SortDirection.ASCENDING).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING);
  try {
    final JSONObject queryResult=notificationRepository.get(query);
    final JSONArray results=queryResult.optJSONArray(Keys.RESULTS);
    ret.put(Pagination.PAGINATION_RECORD_COUNT,queryResult.optJSONObject(Pagination.PAGINATION).optInt(Pagination.PAGINATION_RECORD_COUNT));
    for (int i=0; i < results.length(); i++) {
      final JSONObject notification=results.optJSONObject(i);
      final String commentId=notification.optString(Notification.NOTIFICATION_DATA_ID);
      final int dataType=notification.optInt(Notification.NOTIFICATION_DATA_TYPE);
      final JSONObject followingNotification=new JSONObject();
      followingNotification.put(Notification.NOTIFICATION_DATA_TYPE,dataType);
switch (dataType) {
case Notification.DATA_TYPE_C_FOLLOWING_ARTICLE_COMMENT:
        final JSONObject comment=commentQueryService.getCommentById(commentId);
      final Query q=new Query().setPageCount(1).select(Article.ARTICLE_PERFECT,Article.ARTICLE_TITLE,Article.ARTICLE_TYPE).setFilter(new PropertyFilter(Keys.OBJECT_ID,FilterOperator.EQUAL,comment.optString(Comment.COMMENT_ON_ARTICLE_ID)));
    final JSONArray rlts=articleRepository.get(q).optJSONArray(Keys.RESULTS);
  JSONObject article=rlts.optJSONObject(0);
final String articleTitle=article.optString(Article.ARTICLE_TITLE);
final int articleType=article.optInt(Article.ARTICLE_TYPE);
final int articlePerfect=article.optInt(Article.ARTICLE_PERFECT);
followingNotification.put(Keys.OBJECT_ID,notification.optString(Keys.OBJECT_ID));
followingNotification.put(Common.AUTHOR_NAME,comment.optString(Comment.COMMENT_T_AUTHOR_NAME));
followingNotification.put(Common.CONTENT,comment.optString(Comment.COMMENT_CONTENT));
followingNotification.put(Common.THUMBNAIL_URL,comment.optString(Comment.COMMENT_T_AUTHOR_THUMBNAIL_URL));
followingNotification.put(Article.ARTICLE_TITLE,Emotions.convert(articleTitle));
followingNotification.put(Article.ARTICLE_TYPE,articleType);
followingNotification.put(Common.URL,comment.optString(Comment.COMMENT_SHARP_URL));
followingNotification.put(Common.CREATE_TIME,comment.opt(Comment.COMMENT_CREATE_TIME));
followingNotification.put(Notification.NOTIFICATION_HAS_READ,notification.optBoolean(Notification.NOTIFICATION_HAS_READ));
followingNotification.put(Notification.NOTIFICATION_T_IS_COMMENT,true);
followingNotification.put(Article.ARTICLE_PERFECT,articlePerfect);
rslts.add(followingNotification);
break;
case Notification.DATA_TYPE_C_FOLLOWING_USER:
case Notification.DATA_TYPE_C_FOLLOWING_ARTICLE_UPDATE:
article=articleRepository.get(commentId);
final String articleAuthorId=article.optString(Article.ARTICLE_AUTHOR_ID);
final JSONObject articleAuthor=userRepository.get(articleAuthorId);
followingNotification.put(Keys.OBJECT_ID,notification.optString(Keys.OBJECT_ID));
followingNotification.put(Common.AUTHOR_NAME,articleAuthor.optString(User.USER_NAME));
followingNotification.put(Common.CONTENT,"");
final String thumbnailURL=avatarQueryService.getAvatarURLByUser(articleAuthor,"48");
followingNotification.put(Common.THUMBNAIL_URL,thumbnailURL);
followingNotification.put(Article.ARTICLE_TITLE,Emotions.convert(article.optString(Article.ARTICLE_TITLE)));
followingNotification.put(Article.ARTICLE_TYPE,article.optInt(Article.ARTICLE_TYPE));
followingNotification.put(Common.URL,Latkes.getServePath() + article.optString(Article.ARTICLE_PERMALINK));
followingNotification.put(Common.CREATE_TIME,new Date(article.optLong(Article.ARTICLE_CREATE_TIME)));
followingNotification.put(Notification.NOTIFICATION_HAS_READ,notification.optBoolean(Notification.NOTIFICATION_HAS_READ));
followingNotification.put(Notification.NOTIFICATION_T_IS_COMMENT,false);
final String tagsStr=article.optString(Article.ARTICLE_TAGS);
followingNotification.put(Article.ARTICLE_TAGS,tagsStr);
final List<JSONObject> tags=tagQueryService.buildTagObjs(tagsStr);
followingNotification.put(Article.ARTICLE_T_TAG_OBJS,(Object)tags);
followingNotification.put(Article.ARTICLE_COMMENT_CNT,article.optInt(Article.ARTICLE_COMMENT_CNT));
followingNotification.put(Article.ARTICLE_PERFECT,article.optInt(Article.ARTICLE_PERFECT));
rslts.add(followingNotification);
break;
}
}
return ret;
}
 catch (final RepositoryException e) {
LOGGER.log(Level.ERROR,"Gets [following] notifications",e);
return null;
}
}
