/** 
 * Did collect yesterday's liveness reward?
 * @param userId the specified user id
 * @return {@code true} if collected, returns {@code false} otherwise
 */
public synchronized boolean isCollectedYesterdayLivenessReward(final String userId){
  final Date now=new Date();
  final List<JSONObject> records=pointtransferQueryService.getLatestPointtransfers(userId,Pointtransfer.TRANSFER_TYPE_C_ACTIVITY_YESTERDAY_LIVENESS_REWARD,1);
  if (records.isEmpty()) {
    return false;
  }
  final JSONObject maybeToday=records.get(0);
  final long time=maybeToday.optLong(Pointtransfer.TIME);
  return DateUtils.isSameDay(now,new Date(time));
}
