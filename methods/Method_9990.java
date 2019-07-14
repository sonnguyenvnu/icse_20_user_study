/** 
 * Gets comment count of the specified day.
 * @param day the specified day
 * @return comment count
 */
public int getCommentCntInDay(final Date day){
  final long time=day.getTime();
  final long start=Times.getDayStartTime(time);
  final long end=Times.getDayEndTime(time);
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Keys.OBJECT_ID,FilterOperator.GREATER_THAN_OR_EQUAL,start),new PropertyFilter(Keys.OBJECT_ID,FilterOperator.LESS_THAN,end),new PropertyFilter(Comment.COMMENT_STATUS,FilterOperator.EQUAL,Comment.COMMENT_STATUS_C_VALID)));
  try {
    return (int)commentRepository.count(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Count day comment failed",e);
    return 1;
  }
}
