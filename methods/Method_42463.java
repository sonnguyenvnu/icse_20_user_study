/** 
 * ???? ????+??
 * @param userNo ????
 * @param amount ???????
 * @param requestNo ???
 * @param trxType ????
 * @param remark ??
 */
@Transactional(rollbackFor=Exception.class) public RpAccount unFreezeAmount(String userNo,BigDecimal amount,String requestNo,String trxType,String remark){
  RpAccount account=this.getByUserNo_IsPessimist(userNo,true);
  if (account == null) {
    throw AccountBizException.ACCOUNT_NOT_EXIT;
  }
  Date lastModifyDate=account.getEditTime();
  if (!DateUtils.isSameDayWithToday(lastModifyDate)) {
    account.setTodayExpend(BigDecimal.ZERO);
    account.setTodayIncome(BigDecimal.ZERO);
    account.setTodayExpend(amount);
  }
 else {
    account.setTodayExpend(account.getTodayExpend().add(amount));
  }
  account.setTotalExpend(account.getTodayExpend().add(amount));
  if (account.getUnbalance().subtract(amount).compareTo(BigDecimal.ZERO) == -1) {
    throw AccountBizException.ACCOUNT_UN_FROZEN_AMOUNT_OUTLIMIT;
  }
  account.setEditTime(new Date());
  account.setBalance(account.getBalance().subtract(amount));
  account.setUnbalance(account.getUnbalance().subtract(amount));
  account.setSettAmount(account.getSettAmount().subtract(amount));
  String isAllowSett=PublicEnum.NO.name();
  String completeSett=PublicEnum.NO.name();
  RpAccountHistory accountHistoryEntity=new RpAccountHistory();
  accountHistoryEntity.setCreateTime(new Date());
  accountHistoryEntity.setEditTime(new Date());
  accountHistoryEntity.setIsAllowSett(isAllowSett);
  accountHistoryEntity.setAmount(amount);
  accountHistoryEntity.setBalance(account.getBalance());
  accountHistoryEntity.setRequestNo(requestNo);
  accountHistoryEntity.setIsCompleteSett(completeSett);
  accountHistoryEntity.setRemark(remark);
  accountHistoryEntity.setFundDirection(AccountFundDirectionEnum.SUB.name());
  accountHistoryEntity.setAccountNo(account.getAccountNo());
  accountHistoryEntity.setTrxType(trxType);
  accountHistoryEntity.setUserNo(userNo);
  this.rpAccountHistoryDao.insert(accountHistoryEntity);
  this.rpAccountDao.update(account);
  return account;
}
