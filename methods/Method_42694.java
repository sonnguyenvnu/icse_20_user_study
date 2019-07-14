/** 
 * ??????
 * @param payWayCode
 * @param payTypeCode
 * @param payRate
 */
@Override public void createPayWay(String payProductCode,String payWayCode,String payTypeCode,Double payRate) throws PayBizException {
  RpPayWay payWay=getByPayWayTypeCode(payProductCode,payWayCode,payTypeCode);
  if (payWay != null) {
    throw new PayBizException(PayBizException.PAY_TYPE_IS_EXIST,"???????");
  }
  RpPayProduct rpPayProduct=rpPayProductService.getByProductCode(payProductCode,null);
  if (rpPayProduct.getAuditStatus().equals(PublicEnum.YES.name())) {
    throw new PayBizException(PayBizException.PAY_PRODUCT_IS_EFFECTIVE,"?????????????");
  }
  RpPayWay rpPayWay=new RpPayWay();
  rpPayWay.setPayProductCode(payProductCode);
  rpPayWay.setPayRate(payRate);
  rpPayWay.setPayWayCode(payWayCode);
  rpPayWay.setPayWayName(PayWayEnum.getEnum(payWayCode).getDesc());
  rpPayWay.setPayTypeCode(payTypeCode);
  rpPayWay.setPayTypeName(PayTypeEnum.getEnum(payTypeCode).getDesc());
  rpPayWay.setStatus(PublicStatusEnum.ACTIVE.name());
  rpPayWay.setCreateTime(new Date());
  rpPayWay.setId(StringUtil.get32UUID());
  saveData(rpPayWay);
}
