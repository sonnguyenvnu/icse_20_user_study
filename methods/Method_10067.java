/** 
 * Gets 'broadcast' type notifications with the specified user id, current page number and page size.
 * @param userId         the specified user id
 * @param currentPageNum the specified page number
 * @param pageSize       the specified page size
 * @return result json object, for example,      <pre>{ "paginationRecordCount": int, "rslts": java.util.List[{ "oId": "", // notification record id "authorName": "", "content": "", "thumbnailURL": "", "articleTitle": "", "articleType": int, "articleTags": "", "articleTagObjs": [{}, ....], "articleCommentCnt": int, "url": "", "createTime": java.util.Date, "hasRead": boolean, "type": "", // article/comment }, ....] } </pre>
 */
public JSONObject getBroadcastNotifications(final String userId,final int currentPageNum,final int pageSize){
  final JSONObject ret=new JSONObject();
  final List<JSONObject> rslts=new ArrayList<>();
  ret.put(Keys.RESULTS,(Object)rslts);
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Notification.NOTIFICATION_USER_ID,FilterOperator.EQUAL,userId));
  filters.add(new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,Notification.DATA_TYPE_C_BROADCAST));
  final Query query=new Query().setPage(currentPageNum,pageSize).setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters)).addSort(Notification.NOTIFICATION_HAS_READ,SortDirection.ASCENDING).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING);
  try {
    final JSONObject queryResult=notificationRepository.get(query);
    final JSONArray results=queryResult.optJSONArray(Keys.RESULTS);
    ret.put(Pagination.PAGINATION_RECORD_COUNT,queryResult.optJSONObject(Pagination.PAGINATION).optInt(Pagination.PAGINATION_RECORD_COUNT));
    for (int i=0; i < results.length(); i++) {
      final JSONObject notification=results.optJSONObject(i);
      final String articleId=notification.optString(Notification.NOTIFICATION_DATA_ID);
      final Query q=new Query().setPageCount(1).select(Article.ARTICLE_TITLE,Article.ARTICLE_TYPE,Article.ARTICLE_AUTHOR_ID,Article.ARTICLE_PERMALINK,Article.ARTICLE_CREATE_TIME,Article.ARTICLE_TAGS,Article.ARTICLE_COMMENT_CNT,Article.ARTICLE_PERFECT).setFilter(new PropertyFilter(Keys.OBJECT_ID,FilterOperator.EQUAL,articleId));
      final JSONArray rlts=articleRepository.get(q).optJSONArray(Keys.RESULTS);
      final JSONObject article=rlts.optJSONObject(0);
      if (null == article) {
        LOGGER.warn("Not found article [id=" + articleId + "]");
        continue;
      }
      final String articleTitle=article.optString(Article.ARTICLE_TITLE);
      final String articleAuthorId=article.optString(Article.ARTICLE_AUTHOR_ID);
      final JSONObject author=userRepository.get(articleAuthorId);
      if (null == author) {
        LOGGER.warn("Not found user [id=" + articleAuthorId + "]");
        continue;
      }
      final JSONObject broadcastNotification=new JSONObject();
      broadcastNotification.put(Keys.OBJECT_ID,notification.optString(Keys.OBJECT_ID));
      broadcastNotification.put(Common.AUTHOR_NAME,author.optString(User.USER_NAME));
      broadcastNotification.put(Common.CONTENT,"");
      broadcastNotification.put(Common.THUMBNAIL_URL,avatarQueryService.getAvatarURLByUser(author,"48"));
      broadcastNotification.put(Article.ARTICLE_TITLE,Emotions.convert(articleTitle));
      broadcastNotification.put(Common.URL,Latkes.getServePath() + article.optString(Article.ARTICLE_PERMALINK));
      broadcastNotification.put(Common.CREATE_TIME,new Date(article.optLong(Article.ARTICLE_CREATE_TIME)));
      broadcastNotification.put(Notification.NOTIFICATION_HAS_READ,notification.optBoolean(Notification.NOTIFICATION_HAS_READ));
      broadcastNotification.put(Common.TYPE,Article.ARTICLE);
      broadcastNotification.put(Article.ARTICLE_TYPE,article.optInt(Article.ARTICLE_TYPE));
      final String tagsStr=article.optString(Article.ARTICLE_TAGS);
      broadcastNotification.put(Article.ARTICLE_TAGS,tagsStr);
      final List<JSONObject> tags=tagQueryService.buildTagObjs(tagsStr);
      broadcastNotification.put(Article.ARTICLE_T_TAG_OBJS,(Object)tags);
      broadcastNotification.put(Article.ARTICLE_COMMENT_CNT,article.optInt(Article.ARTICLE_COMMENT_CNT));
      broadcastNotification.put(Article.ARTICLE_PERFECT,article.optInt(Article.ARTICLE_PERFECT));
      rslts.add(broadcastNotification);
    }
    return ret;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Get [broadcast] notifications",e);
    return null;
  }
}
