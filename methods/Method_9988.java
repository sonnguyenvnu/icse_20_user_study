/** 
 * Gets the page number of a comment.
 * @param articleId the specified article id
 * @param commentId the specified comment id
 * @param sortMode  the specified sort mode
 * @param pageSize  the specified comment page size
 * @return page number, return {@code 1} if occurs exception
 */
public int getCommentPage(final String articleId,final String commentId,final int sortMode,final int pageSize){
  final Query numQuery=new Query().setPage(1,Integer.MAX_VALUE).setPageCount(1);
switch (sortMode) {
case UserExt.USER_COMMENT_VIEW_MODE_C_TRADITIONAL:
    numQuery.setFilter(CompositeFilterOperator.and(new PropertyFilter(Comment.COMMENT_ON_ARTICLE_ID,FilterOperator.EQUAL,articleId),new PropertyFilter(Keys.OBJECT_ID,FilterOperator.LESS_THAN,commentId))).addSort(Keys.OBJECT_ID,SortDirection.ASCENDING);
  break;
case UserExt.USER_COMMENT_VIEW_MODE_C_REALTIME:
numQuery.setFilter(CompositeFilterOperator.and(new PropertyFilter(Comment.COMMENT_ON_ARTICLE_ID,FilterOperator.EQUAL,articleId),new PropertyFilter(Keys.OBJECT_ID,FilterOperator.GREATER_THAN,commentId))).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING);
break;
}
Stopwatchs.start("Get comment page");
try {
final long num=commentRepository.count(numQuery);
return (int)((num / pageSize) + 1);
}
 catch (final Exception e) {
LOGGER.log(Level.ERROR,"Gets comment page failed",e);
return 1;
}
 finally {
Stopwatchs.end();
}
}
