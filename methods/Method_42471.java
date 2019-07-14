/** 
 * ??
 */
@Transactional(rollbackFor=Exception.class) public void remit(String settId,String settStatus,String remark){
  RpSettRecord settRecord=rpSettRecordDao.getById(settId);
  if (!settRecord.getSettStatus().equals(SettRecordStatusEnum.CONFIRMED.name())) {
    throw SettBizException.SETT_STATUS_ERROR;
  }
  settRecord.setSettStatus(settStatus);
  settRecord.setEditTime(new Date());
  settRecord.setRemitRemark(remark);
  settRecord.setRemitAmount(settRecord.getSettAmount());
  settRecord.setRemitConfirmTime(new Date());
  settRecord.setRemitRequestTime(new Date());
  rpSettRecordDao.update(settRecord);
  if (settStatus.equals(SettRecordStatusEnum.REMIT_FAIL.name())) {
    rpAccountTransactionService.unFreezeSettAmount(settRecord.getUserNo(),settRecord.getSettAmount());
  }
 else   if (settStatus.equals(SettRecordStatusEnum.REMIT_SUCCESS.name())) {
    rpAccountTransactionService.unFreezeAmount(settRecord.getUserNo(),settRecord.getSettAmount(),settRecord.getId(),TrxTypeEnum.REMIT.name(),remark);
  }
}
