/** 
 * Collects Gobang.
 * @param userId the specified user id
 * @param score  the specified score
 * @return result
 */
public synchronized JSONObject collectGobang(final String userId,final int score){
  final JSONObject ret=new JSONObject().put(Keys.STATUS_CODE,false);
  final boolean succ=null != pointtransferMgmtService.transfer(Pointtransfer.ID_C_SYS,userId,Pointtransfer.TRANSFER_TYPE_C_ACTIVITY_GOBANG_COLLECT,score,"",System.currentTimeMillis(),"");
  if (!succ) {
    ret.put(Keys.MSG,"Sorry, transfer point failed, please contact admin");
  }
  ret.put(Keys.STATUS_CODE,succ);
  return ret;
}
