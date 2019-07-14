/** 
 * ???? ????
 * @param userNo ????
 * @param amount ???????
 * @param requestNo ???
 * @param trxType ????
 * @param remark ??
 */
@Transactional(rollbackFor=Exception.class) public RpAccount unFreezeSettAmount(String userNo,BigDecimal amount){
  RpAccount account=this.getByUserNo_IsPessimist(userNo,true);
  if (account == null) {
    throw AccountBizException.ACCOUNT_NOT_EXIT;
  }
  Date lastModifyDate=account.getEditTime();
  if (!DateUtils.isSameDayWithToday(lastModifyDate)) {
    account.setTodayExpend(BigDecimal.ZERO);
    account.setTodayIncome(BigDecimal.ZERO);
  }
  if (account.getUnbalance().subtract(amount).compareTo(BigDecimal.ZERO) == -1) {
    throw AccountBizException.ACCOUNT_UN_FROZEN_AMOUNT_OUTLIMIT;
  }
  account.setEditTime(new Date());
  account.setUnbalance(account.getUnbalance().subtract(amount));
  this.rpAccountDao.update(account);
  return account;
}
