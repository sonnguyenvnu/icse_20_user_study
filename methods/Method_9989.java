/** 
 * Gets replies of a comment specified by the given comment id.
 * @param currentUserId   the specified current user id, may be null
 * @param commentViewMode the specified comment view mode
 * @param commentId       the given comment id
 * @return a list of replies, return an empty list if not found
 */
public List<JSONObject> getReplies(final String currentUserId,final int commentViewMode,final String commentId){
  final Query query=new Query().addSort(Keys.OBJECT_ID,SortDirection.DESCENDING).setPage(1,Integer.MAX_VALUE).setPageCount(1).setFilter(CompositeFilterOperator.and(new PropertyFilter(Comment.COMMENT_ORIGINAL_COMMENT_ID,FilterOperator.EQUAL,commentId),new PropertyFilter(Comment.COMMENT_STATUS,FilterOperator.EQUAL,Comment.COMMENT_STATUS_C_VALID)));
  try {
    final List<JSONObject> comments=CollectionUtils.jsonArrayToList(commentRepository.get(query).optJSONArray(Keys.RESULTS));
    organizeComments(comments);
    final int pageSize=Symphonys.ARTICLE_COMMENTS_CNT;
    final List<JSONObject> ret=new ArrayList<>();
    for (    final JSONObject comment : comments) {
      final JSONObject reply=new JSONObject();
      ret.add(reply);
      final JSONObject commentAuthor=comment.optJSONObject(Comment.COMMENT_T_COMMENTER);
      if (UserExt.USER_XXX_STATUS_C_PRIVATE == commentAuthor.optInt(UserExt.USER_UA_STATUS)) {
        reply.put(Comment.COMMENT_UA,"");
      }
      reply.put(Comment.COMMENT_T_AUTHOR_NAME,comment.optString(Comment.COMMENT_T_AUTHOR_NAME));
      reply.put(Comment.COMMENT_T_AUTHOR_THUMBNAIL_URL,comment.optString(Comment.COMMENT_T_AUTHOR_THUMBNAIL_URL));
      reply.put(Common.TIME_AGO,comment.optString(Common.TIME_AGO));
      reply.put(Comment.COMMENT_CREATE_TIME_STR,comment.optString(Comment.COMMENT_CREATE_TIME_STR));
      reply.put(Common.REWARED_COUNT,comment.optInt(Comment.COMMENT_THANK_CNT));
      reply.put(Common.REWARDED,comment.optBoolean(Common.REWARDED));
      reply.put(Keys.OBJECT_ID,comment.optString(Keys.OBJECT_ID));
      reply.put(Comment.COMMENT_CONTENT,comment.optString(Comment.COMMENT_CONTENT));
      reply.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,getCommentPage(comment.optString(Comment.COMMENT_ON_ARTICLE_ID),reply.optString(Keys.OBJECT_ID),commentViewMode,pageSize));
      reply.put(Comment.COMMENT_VISIBLE,comment.optInt(Comment.COMMENT_VISIBLE));
      if (Comment.COMMENT_VISIBLE_C_AUTHOR == comment.optInt(Comment.COMMENT_VISIBLE)) {
        final String commentAuthorId=comment.optString(Comment.COMMENT_AUTHOR_ID);
        final String articleId=comment.optString(Comment.COMMENT_ON_ARTICLE_ID);
        final JSONObject article=articleRepository.get(articleId);
        final String articleAuthorId=article.optString(Article.ARTICLE_AUTHOR_ID);
        if (StringUtils.isBlank(currentUserId) || (!StringUtils.equals(currentUserId,commentAuthorId) && !StringUtils.equals(currentUserId,articleAuthorId))) {
          reply.put(Comment.COMMENT_CONTENT,langPropsService.get("onlySelfAndArticleAuthorVisibleLabel"));
        }
      }
    }
    return ret;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Get replies failed",e);
    return Collections.emptyList();
  }
}
