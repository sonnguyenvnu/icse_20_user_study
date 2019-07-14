/** 
 * Gets user count of the specified month.
 * @param month the specified month
 * @return user count
 */
public int getUserCntInMonth(final Date month){
  final long time=month.getTime();
  final long start=Times.getMonthStartTime(time);
  final long end=Times.getMonthEndTime(time);
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Keys.OBJECT_ID,FilterOperator.GREATER_THAN_OR_EQUAL,start),new PropertyFilter(Keys.OBJECT_ID,FilterOperator.LESS_THAN,end),new PropertyFilter(UserExt.USER_STATUS,FilterOperator.EQUAL,UserExt.USER_STATUS_C_VALID)));
  try {
    return (int)userRepository.count(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Count month user failed",e);
    return 1;
  }
}
