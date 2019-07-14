/** 
 * Collects yesterday's liveness reward.
 * @param userId the specified user id
 */
public synchronized void yesterdayLivenessReward(final String userId){
  if (activityQueryService.isCollectedYesterdayLivenessReward(userId)) {
    return;
  }
  final JSONObject yesterdayLiveness=livenessQueryService.getYesterdayLiveness(userId);
  if (null == yesterdayLiveness) {
    return;
  }
  final int sum=Liveness.calcPoint(yesterdayLiveness);
  if (0 == sum) {
    return;
  }
  boolean succ=null != pointtransferMgmtService.transfer(Pointtransfer.ID_C_SYS,userId,Pointtransfer.TRANSFER_TYPE_C_ACTIVITY_YESTERDAY_LIVENESS_REWARD,sum,userId,System.currentTimeMillis(),"");
  if (!succ) {
    return;
  }
  livenessMgmtService.incLiveness(userId,Liveness.LIVENESS_ACTIVITY);
}
