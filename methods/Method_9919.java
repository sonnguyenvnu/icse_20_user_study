/** 
 * Does checkin today?
 * @param userId the specified user id
 * @return {@code true} if checkin succeeded, returns {@code false} otherwise
 */
public synchronized boolean isCheckedinToday(final String userId){
  Stopwatchs.start("Checks checkin");
  try {
    final JSONObject user=userRepository.get(userId);
    final long time=user.optLong(UserExt.USER_CHECKIN_TIME);
    if (DateUtils.isSameDay(new Date(),new Date(time))) {
      return true;
    }
    final List<JSONObject> latestPointtransfers=pointtransferQueryService.getLatestPointtransfers(userId,Pointtransfer.TRANSFER_TYPE_C_ACTIVITY_CHECKIN,1);
    if (latestPointtransfers.isEmpty()) {
      return false;
    }
    final JSONObject latestPointtransfer=latestPointtransfers.get(0);
    final long checkinTime=latestPointtransfer.optLong(Pointtransfer.TIME);
    return DateUtils.isSameDay(new Date(),new Date(checkinTime));
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Checks checkin failed",e);
    return true;
  }
 finally {
    Stopwatchs.end();
  }
}
