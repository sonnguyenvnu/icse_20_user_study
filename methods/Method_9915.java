/** 
 * Gets average point of activity eating snake of a user specified by the given user id.
 * @param userId the given user id
 * @return average point, if the point small than {@code 1}, returns  {@code pointActivityEatingSnake} whichconfigured in sym.properties
 */
public int getEatingSnakeAvgPoint(final String userId){
  return pointtransferRepository.getActivityEatingSnakeAvg(userId);
}
