/** 
 * Bets 1A0001.
 * @param userId       the specified user id
 * @param amount       the specified amount
 * @param smallOrLarge the specified small or large
 * @return result
 */
public synchronized JSONObject bet1A0001(final String userId,final int amount,final int smallOrLarge){
  final JSONObject ret=new JSONObject().put(Keys.STATUS_CODE,false);
  if (activityQueryService.is1A0001Today(userId)) {
    ret.put(Keys.MSG,langPropsService.get("activityParticipatedLabel"));
    return ret;
  }
  final String date=DateFormatUtils.format(new Date(),"yyyyMMdd");
  final boolean succ=null != pointtransferMgmtService.transfer(userId,Pointtransfer.ID_C_SYS,Pointtransfer.TRANSFER_TYPE_C_ACTIVITY_1A0001,amount,date + "-" + smallOrLarge,System.currentTimeMillis(),"");
  ret.put(Keys.STATUS_CODE,succ);
  final String msg=succ ? langPropsService.get("activityBetSuccLabel") : langPropsService.get("activityBetFailLabel");
  ret.put(Keys.MSG,msg);
  livenessMgmtService.incLiveness(userId,Liveness.LIVENESS_ACTIVITY);
  return ret;
}
