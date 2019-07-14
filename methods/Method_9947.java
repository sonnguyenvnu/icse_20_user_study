/** 
 * Makes the top articles with the specified fetch size.
 * @param currentPageNum the specified current page number
 * @param fetchSize      the specified fetch size
 * @return top articles query
 */
private Query makeTopQuery(final int currentPageNum,final int fetchSize){
  final Query query=new Query().addSort(Article.REDDIT_SCORE,SortDirection.DESCENDING).addSort(Article.ARTICLE_LATEST_CMT_TIME,SortDirection.DESCENDING).setPageCount(1).setPage(currentPageNum,fetchSize);
  query.setFilter(makeArticleShowingFilter());
  return query;
}
