/** 
 * ??????
 * @param userNo ????
 * @param freezeAmount ????
 */
@Transactional(rollbackFor=Exception.class) public RpAccount freezeAmount(String userNo,BigDecimal freezeAmount){
  RpAccount account=this.getByUserNo_IsPessimist(userNo,true);
  if (account == null) {
    throw AccountBizException.ACCOUNT_NOT_EXIT;
  }
  account.setEditTime(new Date());
  if (!account.availableBalanceIsEnough(freezeAmount)) {
    throw AccountBizException.ACCOUNT_FROZEN_AMOUNT_OUTLIMIT;
  }
  account.setUnbalance(account.getUnbalance().add(freezeAmount));
  this.rpAccountDao.update(account);
  return account;
}
