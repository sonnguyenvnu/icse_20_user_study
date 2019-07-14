/** 
 * Gets comment count of the specified month.
 * @param day the specified month
 * @return comment count
 */
public int getCommentCntInMonth(final Date day){
  final long time=day.getTime();
  final long start=Times.getMonthStartTime(time);
  final long end=Times.getMonthEndTime(time);
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Keys.OBJECT_ID,FilterOperator.GREATER_THAN_OR_EQUAL,start),new PropertyFilter(Keys.OBJECT_ID,FilterOperator.LESS_THAN,end),new PropertyFilter(Comment.COMMENT_STATUS,FilterOperator.EQUAL,Comment.COMMENT_STATUS_C_VALID)));
  try {
    return (int)commentRepository.count(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Count month comment failed",e);
    return 1;
  }
}
