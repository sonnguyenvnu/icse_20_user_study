/** 
 * ????????.<br/>
 * @param userEnterpriseList ????.<br/>
 */
public void launchAutoSett(List<RpAccount> accountList){
  if (accountList == null || accountList.isEmpty()) {
    return;
  }
  for (  RpAccount rpAccount : accountList) {
    try {
      RpUserPayConfig rpUserPayConfig=rpUserPayConfigService.getByUserNo(rpAccount.getUserNo());
      if (rpUserPayConfig == null) {
        LOG.info(rpAccount.getUserNo() + "??????????????");
        continue;
      }
      if (rpUserPayConfig.getIsAutoSett().equals(PublicEnum.YES.name())) {
        LOG.debug(rpAccount.getUserNo() + ":??????");
        rpSettHandleService.launchAutoSett(rpAccount.getUserNo());
        LOG.debug(rpAccount.getUserNo() + ":??????");
      }
 else {
        LOG.info(rpAccount.getUserNo() + ":???????");
      }
    }
 catch (    Exception e) {
      LOG.error("???????" + rpAccount.getUserNo(),e);
    }
  }
}
