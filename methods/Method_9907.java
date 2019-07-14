/** 
 * Collects eating snake.
 * @param userId the specified user id
 * @param score  the specified score
 * @return result
 */
public synchronized JSONObject collectEatingSnake(final String userId,final int score){
  final JSONObject ret=new JSONObject().put(Keys.STATUS_CODE,false);
  if (score < 1) {
    ret.put(Keys.STATUS_CODE,true);
    return ret;
  }
  final int max=Symphonys.POINT_ACTIVITY_EATINGSNAKE_COLLECT_MAX;
  final int amout=score > max ? max : score;
  final boolean succ=null != pointtransferMgmtService.transfer(Pointtransfer.ID_C_SYS,userId,Pointtransfer.TRANSFER_TYPE_C_ACTIVITY_EATINGSNAKE_COLLECT,amout,"",System.currentTimeMillis(),"");
  if (!succ) {
    ret.put(Keys.MSG,"Sorry, transfer point failed, please contact admin");
  }
  ret.put(Keys.STATUS_CODE,succ);
  return ret;
}
