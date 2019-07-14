/** 
 * Starts eating snake.
 * @param userId the specified user id
 * @return result
 */
public synchronized JSONObject startEatingSnake(final String userId){
  final JSONObject ret=new JSONObject().put(Keys.STATUS_CODE,false);
  final int startPoint=pointtransferRepository.getActivityEatingSnakeAvg(userId);
  final boolean succ=null != pointtransferMgmtService.transfer(userId,Pointtransfer.ID_C_SYS,Pointtransfer.TRANSFER_TYPE_C_ACTIVITY_EATINGSNAKE,startPoint,"",System.currentTimeMillis(),"");
  ret.put(Keys.STATUS_CODE,succ);
  final String msg=succ ? "started" : langPropsService.get("activityStartEatingSnakeFailLabel");
  ret.put(Keys.MSG,msg);
  livenessMgmtService.incLiveness(userId,Liveness.LIVENESS_ACTIVITY);
  return ret;
}
