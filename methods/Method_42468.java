/** 
 * ????
 * @param userNo
 * @param accountNo
 * @param settAmount
 * @param bankAccount
 * @param settType ??????:?????
 */
@Transactional(rollbackFor=Exception.class) private void launchSett(String userNo,String userName,String accountNo,BigDecimal settAmount,RpUserBankAccount bankAccount,String settType){
  RpSettRecord settRecord=new RpSettRecord();
  settRecord.setAccountNo(accountNo);
  settRecord.setCountry("??");
  settRecord.setProvince(bankAccount.getProvince());
  settRecord.setCity(bankAccount.getCity());
  settRecord.setAreas(bankAccount.getAreas());
  settRecord.setBankAccountAddress(bankAccount.getStreet());
  settRecord.setBankAccountName(bankAccount.getBankAccountName());
  settRecord.setBankCode(bankAccount.getBankCode());
  settRecord.setBankName(bankAccount.getBankName());
  settRecord.setBankAccountNo(bankAccount.getBankAccountNo());
  settRecord.setBankAccountType(bankAccount.getBankAccountType());
  settRecord.setOperatorLoginname("");
  settRecord.setOperatorRealname("");
  settRecord.setRemitAmount(settAmount);
  settRecord.setRemitRequestTime(new Date());
  settRecord.setSettAmount(settAmount);
  settRecord.setSettFee(BigDecimal.ZERO);
  settRecord.setSettMode(settType);
  settRecord.setSettStatus(SettRecordStatusEnum.WAIT_CONFIRM.name());
  settRecord.setUserName(userName);
  settRecord.setUserNo(userNo);
  settRecord.setMobileNo(bankAccount.getMobileNo());
  settRecord.setEditTime(new Date());
  rpSettRecordDao.insert(settRecord);
  rpAccountTransactionService.freezeAmount(userNo,settAmount);
}
