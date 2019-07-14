/** 
 * Gets the offered (accepted) comment of an article specified by the given article id.
 * @param commentViewMode the specified comment view mode
 * @param articleId       the given article id
 * @return accepted comment, return {@code null} if not found
 */
public JSONObject getOfferedComment(final int commentViewMode,final String articleId){
  Stopwatchs.start("Gets accepted comment");
  try {
    final Query query=new Query().addSort(Comment.COMMENT_SCORE,SortDirection.DESCENDING).setPage(1,1).setPageCount(1).setFilter(CompositeFilterOperator.and(new PropertyFilter(Comment.COMMENT_ON_ARTICLE_ID,FilterOperator.EQUAL,articleId),new PropertyFilter(Comment.COMMENT_QNA_OFFERED,FilterOperator.EQUAL,Comment.COMMENT_QNA_OFFERED_C_YES),new PropertyFilter(Comment.COMMENT_STATUS,FilterOperator.EQUAL,Comment.COMMENT_STATUS_C_VALID)));
    try {
      final List<JSONObject> comments=CollectionUtils.jsonArrayToList(commentRepository.get(query).optJSONArray(Keys.RESULTS));
      if (comments.isEmpty()) {
        return null;
      }
      final JSONObject ret=comments.get(0);
      organizeComment(ret);
      final int pageSize=Symphonys.ARTICLE_COMMENTS_CNT;
      ret.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,getCommentPage(articleId,ret.optString(Keys.OBJECT_ID),commentViewMode,pageSize));
      return ret;
    }
 catch (    final RepositoryException e) {
      LOGGER.log(Level.ERROR,"Gets accepted comment failed",e);
      return null;
    }
  }
  finally {
    Stopwatchs.end();
  }
}
