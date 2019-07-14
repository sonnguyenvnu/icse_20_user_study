/** 
 * ????
 */
public void audit(String settId,String settStatus,String remark){
  RpSettRecord settRecord=rpSettRecordDao.getById(settId);
  if (!settRecord.getSettStatus().equals(SettRecordStatusEnum.WAIT_CONFIRM.name())) {
    throw SettBizException.SETT_STATUS_ERROR;
  }
  settRecord.setSettStatus(settStatus);
  settRecord.setEditTime(new Date());
  settRecord.setRemark(remark);
  rpSettRecordDao.update(settRecord);
  if (settStatus.equals(SettRecordStatusEnum.CANCEL.name())) {
    rpAccountTransactionService.unFreezeSettAmount(settRecord.getUserNo(),settRecord.getSettAmount());
  }
}
