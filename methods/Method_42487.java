/** 
 * ????????
 * @param rpNotifyRecordLog
 * @return
 */
@Override public long createNotifyRecordLog(RpNotifyRecordLog rpNotifyRecordLog){
  return rpNotifyRecordLogDao.insert(rpNotifyRecordLog);
}
