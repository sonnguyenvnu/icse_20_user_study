/** 
 * ??????
 * @param productCode
 */
@Override public void deletePayProduct(String productCode) throws PayBizException {
  List<RpPayWay> payWayList=rpPayWayService.listByProductCode(productCode);
  if (!payWayList.isEmpty()) {
    throw new PayBizException(PayBizException.PAY_PRODUCT_HAS_DATA,"?????????????????");
  }
  List<RpUserPayConfig> payConfigList=rpUserPayConfigService.listByProductCode(productCode);
  if (!payConfigList.isEmpty()) {
    throw new PayBizException(PayBizException.PAY_PRODUCT_HAS_DATA,"???????????????");
  }
  RpPayProduct rpPayProduct=getByProductCode(productCode,null);
  if (rpPayProduct.getAuditStatus().equals(PublicEnum.YES.name())) {
    throw new PayBizException(PayBizException.PAY_PRODUCT_IS_EFFECTIVE,"?????????????");
  }
  rpPayProduct.setStatus(PublicStatusEnum.UNACTIVE.name());
  updateData(rpPayProduct);
}
