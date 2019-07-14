/** 
 * ????????.<br/>
 * @param notifyRecord
 * @return
 */
public long saveNotifyRecord(RpNotifyRecord notifyRecord){
  return rpNotifyService.createNotifyRecord(notifyRecord);
}
