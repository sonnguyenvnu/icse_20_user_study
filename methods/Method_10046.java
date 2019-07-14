/** 
 * Gets point of current liveness.
 * @param userId the specified user id
 * @return point
 */
public int getCurrentLivenessPoint(final String userId){
  Stopwatchs.start("Gets liveness");
  try {
    final String date=DateFormatUtils.format(new Date(),"yyyyMMdd");
    try {
      final JSONObject liveness=livenessRepository.getByUserAndDate(userId,date);
      if (null == liveness) {
        return 0;
      }
      return Liveness.calcPoint(liveness);
    }
 catch (    final RepositoryException e) {
      LOGGER.log(Level.ERROR,"Gets current liveness point failed",e);
      return 0;
    }
  }
  finally {
    Stopwatchs.end();
  }
}
