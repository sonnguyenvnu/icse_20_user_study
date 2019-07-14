/** 
 * Removes a comment specified with the given comment id. Calls this method will remove all existed data related with the specified comment forcibly.
 * @param commentId the given comment id
 * @throws RepositoryException repository exception
 */
public void removeComment(final String commentId) throws RepositoryException {
  final JSONObject comment=get(commentId);
  if (null == comment) {
    return;
  }
  remove(comment.optString(Keys.OBJECT_ID));
  final String commentAuthorId=comment.optString(Comment.COMMENT_AUTHOR_ID);
  final JSONObject commenter=userRepository.get(commentAuthorId);
  int commentCount=commenter.optInt(UserExt.USER_COMMENT_COUNT) - 1;
  if (0 > commentCount) {
    commentCount=0;
  }
  commenter.put(UserExt.USER_COMMENT_COUNT,commentCount);
  userRepository.update(commentAuthorId,commenter);
  final String articleId=comment.optString(Comment.COMMENT_ON_ARTICLE_ID);
  final JSONObject article=articleRepository.get(articleId);
  article.put(Article.ARTICLE_COMMENT_CNT,article.optInt(Article.ARTICLE_COMMENT_CNT) - 1);
  if (0 < article.optInt(Article.ARTICLE_COMMENT_CNT)) {
    final Query latestCmtQuery=new Query().setFilter(new PropertyFilter(Comment.COMMENT_ON_ARTICLE_ID,FilterOperator.EQUAL,articleId)).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING).setPage(1,1);
    final JSONObject latestCmt=get(latestCmtQuery).optJSONArray(Keys.RESULTS).optJSONObject(0);
    article.put(Article.ARTICLE_LATEST_CMT_TIME,latestCmt.optLong(Keys.OBJECT_ID));
    final JSONObject latestCmtAuthor=userRepository.get(latestCmt.optString(Comment.COMMENT_AUTHOR_ID));
    article.put(Article.ARTICLE_LATEST_CMTER_NAME,latestCmtAuthor.optString(User.USER_NAME));
  }
 else {
    article.put(Article.ARTICLE_LATEST_CMT_TIME,articleId);
    article.put(Article.ARTICLE_LATEST_CMTER_NAME,"");
  }
  articleRepository.update(articleId,article);
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Revision.REVISION_DATA_ID,FilterOperator.EQUAL,commentId),new PropertyFilter(Revision.REVISION_DATA_TYPE,FilterOperator.EQUAL,Revision.DATA_TYPE_C_COMMENT)));
  final JSONArray commentRevisions=revisionRepository.get(query).optJSONArray(Keys.RESULTS);
  for (int j=0; j < commentRevisions.length(); j++) {
    final JSONObject articleRevision=commentRevisions.optJSONObject(j);
    revisionRepository.remove(articleRevision.optString(Keys.OBJECT_ID));
  }
  final JSONObject commentCntOption=optionRepository.get(Option.ID_C_STATISTIC_CMT_COUNT);
  commentCntOption.put(Option.OPTION_VALUE,commentCntOption.optInt(Option.OPTION_VALUE) - 1);
  optionRepository.update(Option.ID_C_STATISTIC_CMT_COUNT,commentCntOption);
  final String originalCommentId=comment.optString(Comment.COMMENT_ORIGINAL_COMMENT_ID);
  if (StringUtils.isNotBlank(originalCommentId)) {
    final JSONObject originalComment=get(originalCommentId);
    if (null != originalComment) {
      originalComment.put(Comment.COMMENT_REPLY_CNT,originalComment.optInt(Comment.COMMENT_REPLY_CNT) - 1);
      update(originalCommentId,originalComment);
    }
  }
  notificationRepository.removeByDataId(commentId);
}
