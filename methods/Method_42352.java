/** 
 * ?????????????.<br/>
 * @param userEnterpriseList ????.<br/>
 * @param collectDate ??????(????????)
 */
public void launchDailySettCollect(List<RpAccount> accountList,Date endDate){
  if (accountList == null || accountList.isEmpty()) {
    return;
  }
  for (  RpAccount rpAccount : accountList) {
    try {
      LOG.debug(rpAccount.getUserNo() + ":????");
      dailySettCollectBiz.dailySettCollect(rpAccount,endDate);
      LOG.debug(rpAccount.getUserNo() + ":????");
    }
 catch (    Exception e) {
      LOG.error(rpAccount.getUserNo() + ":????",e);
    }
  }
}
