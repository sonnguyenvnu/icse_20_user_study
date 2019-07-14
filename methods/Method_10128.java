/** 
 * Gets user count of the specified day.
 * @param day the specified day
 * @return user count
 */
public int getUserCntInDay(final Date day){
  final long time=day.getTime();
  final long start=Times.getDayStartTime(time);
  final long end=Times.getDayEndTime(time);
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Keys.OBJECT_ID,FilterOperator.GREATER_THAN_OR_EQUAL,start),new PropertyFilter(Keys.OBJECT_ID,FilterOperator.LESS_THAN,end),new PropertyFilter(UserExt.USER_STATUS,FilterOperator.EQUAL,UserExt.USER_STATUS_C_VALID)));
  try {
    return (int)userRepository.count(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Count day user failed",e);
    return 1;
  }
}
