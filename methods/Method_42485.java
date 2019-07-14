/** 
 * ??????
 * @param rpNotifyRecord
 */
@Override public long createNotifyRecord(RpNotifyRecord rpNotifyRecord){
  return rpNotifyRecordDao.insert(rpNotifyRecord);
}
