/** 
 * Gets article count of the specified month.
 * @param day the specified month
 * @return article count
 */
public int getArticleCntInMonth(final Date day){
  final long time=day.getTime();
  final long start=Times.getMonthStartTime(time);
  final long end=Times.getMonthEndTime(time);
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Keys.OBJECT_ID,FilterOperator.GREATER_THAN_OR_EQUAL,start),new PropertyFilter(Keys.OBJECT_ID,FilterOperator.LESS_THAN,end),new PropertyFilter(Article.ARTICLE_STATUS,FilterOperator.NOT_EQUAL,Article.ARTICLE_STATUS_C_INVALID)));
  try {
    return (int)articleRepository.count(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Count month article failed",e);
    return 1;
  }
}
