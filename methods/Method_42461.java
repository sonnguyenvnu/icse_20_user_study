/** 
 * ??:?????
 * @param userNo ????
 * @param amount ????
 * @param requestNo ???
 * @param trxType ????
 * @param remark ??
 */
@Transactional(rollbackFor=Exception.class) public RpAccount debitToAccount(String userNo,BigDecimal amount,String requestNo,String bankTrxNo,String trxType,String remark){
  RpAccount account=this.getByUserNo_IsPessimist(userNo,true);
  if (account == null) {
    throw AccountBizException.ACCOUNT_NOT_EXIT;
  }
  BigDecimal availableBalance=account.getAvailableBalance();
  String isAllowSett=PublicEnum.YES.name();
  String completeSett=PublicEnum.NO.name();
  if (availableBalance.compareTo(amount) == -1) {
    throw AccountBizException.ACCOUNT_SUB_AMOUNT_OUTLIMIT;
  }
  account.setBalance(account.getBalance().subtract(amount));
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
  account.setEditTime(new Date());
  RpAccountHistory accountHistoryEntity=new RpAccountHistory();
  accountHistoryEntity.setCreateTime(new Date());
  accountHistoryEntity.setEditTime(new Date());
  accountHistoryEntity.setIsAllowSett(isAllowSett);
  accountHistoryEntity.setAmount(amount);
  accountHistoryEntity.setBalance(account.getBalance());
  accountHistoryEntity.setRequestNo(requestNo);
  accountHistoryEntity.setBankTrxNo(bankTrxNo);
  accountHistoryEntity.setIsCompleteSett(completeSett);
  accountHistoryEntity.setRemark(remark);
  accountHistoryEntity.setFundDirection(AccountFundDirectionEnum.SUB.name());
  accountHistoryEntity.setAccountNo(account.getAccountNo());
  accountHistoryEntity.setTrxType(trxType);
  accountHistoryEntity.setId(StringUtil.get32UUID());
  accountHistoryEntity.setUserNo(userNo);
  this.rpAccountHistoryDao.insert(accountHistoryEntity);
  this.rpAccountDao.update(account);
  return account;
}
