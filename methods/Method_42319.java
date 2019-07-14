/** 
 * ????????.<br/>
 * @param id
 * @param notifyTimes ????.<br/>
 * @param status ????.<br/>
 * @return ????
 */
public void updateNotifyRord(String id,int notifyTimes,String status){
  RpNotifyRecord notifyRecord=rpNotifyService.getNotifyRecordById(id);
  notifyRecord.setNotifyTimes(notifyTimes);
  notifyRecord.setStatus(status);
  notifyRecord.setLastNotifyTime(new Date());
  rpNotifyService.updateNotifyRecord(notifyRecord);
}
