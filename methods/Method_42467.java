/** 
 * ????--?????
 * @param userNo
 * @param accountNo
 * @param settAmount
 * @param bankAccount
 */
public void launchSett(String userNo,BigDecimal settAmount){
  RpAccount account=rpAccountQueryService.getAccountByUserNo(userNo);
  RpUserInfo userInfo=rpUserInfoService.getDataByMerchentNo(userNo);
  RpUserBankAccount rpUserBankAccount=rpUserBankAccountService.getByUserNo(userNo);
  BigDecimal availableAmount=account.getAvailableSettAmount();
  if (settAmount.compareTo(availableAmount) > 0) {
    throw AccountBizException.ACCOUNT_SUB_AMOUNT_OUTLIMIT;
  }
  if (rpUserBankAccount == null) {
    throw UserBizException.USER_BANK_ACCOUNT_IS_NULL;
  }
  String settType=SettModeTypeEnum.SELFHELP_SETTLE.name();
  this.launchSett(userNo,userInfo.getUserName(),account.getAccountNo(),settAmount,rpUserBankAccount,settType);
}
