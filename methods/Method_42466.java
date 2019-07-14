/** 
 * ??????????????????.<br/>
 * @param userNo ????.
 * @param endDate ??????.
 * @param riskDay ?????.
 * @param userName ????
 * @param codeNum ????
 */
@Transactional(rollbackFor=Exception.class) public void dailySettlementCollect(String userNo,Date endDate,int riskDay,String userName){
  RpAccount account=rpAccountQueryService.getAccountByUserNo(userNo);
  String endDateStr=DateUtils.formatDate(endDate,"yyyy-MM-dd");
  List<DailyCollectAccountHistoryVo> accountHistoryList=rpAccountQueryService.listDailyCollectAccountHistoryVo(account.getAccountNo(),endDateStr,riskDay,null);
  BigDecimal totalAmount=BigDecimal.ZERO;
  for (  DailyCollectAccountHistoryVo collectVo : accountHistoryList) {
    totalAmount=totalAmount.add(collectVo.getTotalAmount());
    RpSettDailyCollect dailyCollect=new RpSettDailyCollect();
    dailyCollect.setAccountNo(collectVo.getAccountNo());
    dailyCollect.setUserName(userName);
    dailyCollect.setCollectDate(collectVo.getCollectDate());
    dailyCollect.setCollectType(SettDailyCollectTypeEnum.ALL.name());
    dailyCollect.setTotalAmount(collectVo.getTotalAmount());
    dailyCollect.setTotalCount(collectVo.getTotalNum());
    dailyCollect.setSettStatus(SettDailyCollectStatusEnum.SETTLLED.name());
    dailyCollect.setRiskDay(collectVo.getRiskDay());
    dailyCollect.setRemark("");
    dailyCollect.setEditTime(new Date());
    rpSettDailyCollectDao.insert(dailyCollect);
  }
  rpAccountTransactionService.settCollectSuccess(userNo,endDateStr,riskDay,totalAmount);
}
