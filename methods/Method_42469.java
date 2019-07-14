/** 
 * ??????
 * @param userNo
 */
public void launchAutoSett(String userNo){
  RpUserInfo userInfo=rpUserInfoService.getDataByMerchentNo(userNo);
  RpAccount account=rpAccountQueryService.getAccountByUserNo(userNo);
  BigDecimal settAmount=account.getAvailableSettAmount();
  String settMinAmount=AccountConfigUtil.readConfig("sett_min_amount");
  if (settAmount.compareTo(new BigDecimal(settMinAmount)) == -1) {
    throw new BizException("?????????????:" + settMinAmount);
  }
  RpUserBankAccount rpUserBankAccount=rpUserBankAccountService.getByUserNo(userNo);
  if (rpUserBankAccount == null) {
    throw new BizException("???????????????????");
  }
  String bankType=rpUserBankAccount.getBankAccountType();
  if (bankType.equals(BankAccountTypeEnum.PRIVATE_DEBIT_ACCOUNT.name())) {
    String settMaxAmount=AccountConfigUtil.readConfig("sett_max_amount");
    if (settAmount.compareTo(new BigDecimal(settMaxAmount)) == 1) {
      throw new BizException("?????????????:" + settMaxAmount);
    }
  }
  String userName=userInfo.getUserName();
  String accountNo=account.getAccountNo();
  String settType=SettModeTypeEnum.REGULAR_SETTLE.name();
  this.launchSett(userNo,userName,accountNo,settAmount,rpUserBankAccount,settType);
}
