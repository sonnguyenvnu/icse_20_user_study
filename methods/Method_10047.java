/** 
 * Gets the yesterday's liveness.
 * @param userId the specified user id
 * @return yesterday's liveness, returns {@code null} if not found
 */
public JSONObject getYesterdayLiveness(final String userId){
  final Date yesterday=DateUtils.addDays(new Date(),-1);
  final String date=DateFormatUtils.format(yesterday,"yyyyMMdd");
  try {
    return livenessRepository.getByUserAndDate(userId,date);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets yesterday's liveness failed",e);
    return null;
  }
}
