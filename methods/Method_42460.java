/** 
 * ??:?????
 * @param userNo ????
 * @param amount ????
 * @param requestNo ???
 * @param trxType ????
 * @param remark ??
 */
@Transactional(rollbackFor=Exception.class) public RpAccount creditToAccount(String userNo,BigDecimal amount,String requestNo,String bankTrxNo,String trxType,String remark){
  RpAccount account=this.getByUserNo_IsPessimist(userNo,true);
  if (account == null) {
    throw AccountBizException.ACCOUNT_NOT_EXIT;
  }
  Date lastModifyDate=account.getEditTime();
  if (!DateUtils.isSameDayWithToday(lastModifyDate)) {
    account.setTodayExpend(BigDecimal.ZERO);
    account.setTodayIncome(BigDecimal.ZERO);
  }
  if (TrxTypeEnum.EXPENSE.name().equals(trxType)) {
    account.setTotalIncome(account.getTotalIncome().add(amount));
    if (DateUtils.isSameDayWithToday(lastModifyDate)) {
      account.setTodayIncome(account.getTodayIncome().add(amount));
    }
 else {
      account.setTodayIncome(amount);
    }
  }
  String completeSett=PublicEnum.NO.name();
  String isAllowSett=PublicEnum.YES.name();
  account.setBalance(account.getBalance().add(amount));
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
  accountHistoryEntity.setFundDirection(AccountFundDirectionEnum.ADD.name());
  accountHistoryEntity.setAccountNo(account.getAccountNo());
  accountHistoryEntity.setTrxType(trxType);
  accountHistoryEntity.setId(StringUtil.get32UUID());
  accountHistoryEntity.setUserNo(userNo);
  this.rpAccountHistoryDao.insert(accountHistoryEntity);
  this.rpAccountDao.update(account);
  LOG.info("???????????????");
  return account;
}
