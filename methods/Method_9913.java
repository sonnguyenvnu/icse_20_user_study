/** 
 * Starts Gobang.
 * @param userId the specified user id
 * @return result
 */
public synchronized JSONObject startGobang(final String userId){
  final JSONObject ret=new JSONObject().put(Keys.STATUS_CODE,false);
  final int startPoint=Pointtransfer.TRANSFER_SUM_C_ACTIVITY_GOBANG_START;
  final boolean succ=null != pointtransferMgmtService.transfer(userId,Pointtransfer.ID_C_SYS,Pointtransfer.TRANSFER_TYPE_C_ACTIVITY_GOBANG,startPoint,"",System.currentTimeMillis(),"");
  ret.put(Keys.STATUS_CODE,succ);
  final String msg=succ ? "started" : langPropsService.get("activityStartGobangFailLabel");
  ret.put(Keys.MSG,msg);
  livenessMgmtService.incLiveness(userId,Liveness.LIVENESS_ACTIVITY);
  return ret;
}
