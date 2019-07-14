/** 
 * ??????????????????????
 * @param userNo ????
 * @param collectDate ????
 * @param riskDay ?????
 * @param totalAmount ???????
 */
@Transactional(rollbackFor=Exception.class) public void settCollectSuccess(String userNo,String collectDate,int riskDay,BigDecimal totalAmount){
  LOG.info("==>settCollectSuccess");
  LOG.info(String.format("==>userNo:%s, collectDate:%s, riskDay:%s",userNo,collectDate,riskDay));
  RpAccount account=this.getByUserNo_IsPessimist(userNo,true);
  if (account == null) {
    throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("?????,????{%s}",userNo).print();
  }
  Map<String,Object> params=new HashMap<String,Object>();
  params.put("accountNo",account.getAccountNo());
  params.put("statDate",collectDate);
  params.put("riskDay",riskDay);
  rpAccountHistoryDao.updateCompleteSettTo100(params);
  account.setSettAmount(account.getSettAmount().add(totalAmount));
  rpAccountDao.update(account);
  LOG.info("==>settCollectSuccess<==");
}
