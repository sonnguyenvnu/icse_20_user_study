/** 
 * ????????
 */
@Override public void updateUserPayConfig(String userNo,String productCode,String productName,Integer riskDay,String fundIntoType,String isAutoSett,String appId,String merchantId,String partnerKey,String ali_partner,String ali_sellerId,String ali_key,String ali_appid,String ali_rsaPrivateKey,String ali_rsaPublicKey,String securityRating,String merchantServerIp) throws PayBizException {
  RpUserPayConfig rpUserPayConfig=rpUserPayConfigDao.getByUserNo(userNo,null);
  if (rpUserPayConfig == null) {
    throw new PayBizException(PayBizException.USER_PAY_CONFIG_IS_NOT_EXIST,"?????????");
  }
  rpUserPayConfig.setProductCode(productCode);
  rpUserPayConfig.setProductName(productName);
  rpUserPayConfig.setRiskDay(riskDay);
  rpUserPayConfig.setFundIntoType(fundIntoType);
  rpUserPayConfig.setIsAutoSett(isAutoSett);
  rpUserPayConfig.setEditTime(new Date());
  rpUserPayConfig.setSecurityRating(securityRating);
  rpUserPayConfig.setMerchantServerIp(merchantServerIp);
  updateData(rpUserPayConfig);
  List<RpPayWay> payWayList=rpPayWayService.listByProductCode(productCode);
  Map<String,String> map=new HashMap<String,String>();
  for (  RpPayWay payWay : payWayList) {
    map.put(payWay.getPayWayCode(),payWay.getPayWayName());
  }
  for (  String key : map.keySet()) {
    if (key.equals(PayWayEnum.WEIXIN.name())) {
      RpUserPayInfo rpUserPayInfo=rpUserPayInfoService.getByUserNo(userNo,PayWayEnum.WEIXIN.name());
      if (rpUserPayInfo == null) {
        rpUserPayInfo=new RpUserPayInfo();
        rpUserPayInfo.setId(StringUtil.get32UUID());
        rpUserPayInfo.setCreateTime(new Date());
        rpUserPayInfo.setAppId(appId);
        rpUserPayInfo.setMerchantId(merchantId);
        rpUserPayInfo.setPartnerKey(partnerKey);
        rpUserPayInfo.setPayWayCode(PayWayEnum.WEIXIN.name());
        rpUserPayInfo.setPayWayName(PayWayEnum.WEIXIN.getDesc());
        rpUserPayInfo.setUserNo(userNo);
        rpUserPayInfo.setUserName(rpUserPayConfig.getUserName());
        rpUserPayInfo.setStatus(PublicStatusEnum.ACTIVE.name());
        rpUserPayInfoService.saveData(rpUserPayInfo);
      }
 else {
        rpUserPayInfo.setEditTime(new Date());
        rpUserPayInfo.setAppId(appId);
        rpUserPayInfo.setMerchantId(merchantId);
        rpUserPayInfo.setPartnerKey(partnerKey);
        rpUserPayInfo.setPayWayCode(PayWayEnum.WEIXIN.name());
        rpUserPayInfo.setPayWayName(PayWayEnum.WEIXIN.getDesc());
        rpUserPayInfoService.updateData(rpUserPayInfo);
      }
    }
 else     if (key.equals(PayWayEnum.ALIPAY.name())) {
      RpUserPayInfo rpUserPayInfo=rpUserPayInfoService.getByUserNo(userNo,PayWayEnum.ALIPAY.name());
      if (rpUserPayInfo == null) {
        rpUserPayInfo=new RpUserPayInfo();
        rpUserPayInfo.setId(StringUtil.get32UUID());
        rpUserPayInfo.setCreateTime(new Date());
        rpUserPayInfo.setAppId(ali_partner);
        rpUserPayInfo.setMerchantId(ali_sellerId);
        rpUserPayInfo.setPartnerKey(ali_key);
        rpUserPayInfo.setPayWayCode(PayWayEnum.ALIPAY.name());
        rpUserPayInfo.setPayWayName(PayWayEnum.ALIPAY.getDesc());
        rpUserPayInfo.setUserNo(userNo);
        rpUserPayInfo.setUserName(rpUserPayConfig.getUserName());
        rpUserPayInfo.setStatus(PublicStatusEnum.ACTIVE.name());
        rpUserPayInfo.setOfflineAppId(ali_appid);
        rpUserPayInfo.setRsaPrivateKey(ali_rsaPrivateKey);
        rpUserPayInfo.setRsaPublicKey(ali_rsaPublicKey);
        rpUserPayInfoService.saveData(rpUserPayInfo);
      }
 else {
        rpUserPayInfo.setEditTime(new Date());
        rpUserPayInfo.setAppId(ali_partner);
        rpUserPayInfo.setMerchantId(ali_sellerId);
        rpUserPayInfo.setPartnerKey(ali_key);
        rpUserPayInfo.setPayWayCode(PayWayEnum.ALIPAY.name());
        rpUserPayInfo.setPayWayName(PayWayEnum.ALIPAY.getDesc());
        rpUserPayInfo.setOfflineAppId(ali_appid);
        rpUserPayInfo.setRsaPrivateKey(ali_rsaPrivateKey);
        rpUserPayInfo.setRsaPublicKey(ali_rsaPublicKey);
        rpUserPayInfoService.updateData(rpUserPayInfo);
      }
    }
  }
}
