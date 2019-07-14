/** 
 * ??
 * @param userNo
 * @param auditStatus
 */
@Override public void audit(String userNo,String auditStatus){
  RpUserPayConfig rpUserPayConfig=getByUserNo(userNo,null);
  if (rpUserPayConfig == null) {
    throw new PayBizException(PayBizException.USER_PAY_CONFIG_IS_NOT_EXIST,"????????");
  }
  if (auditStatus.equals(PublicEnum.YES.name())) {
    RpPayProduct rpPayProduct=rpPayProductService.getByProductCode(rpUserPayConfig.getProductCode(),PublicEnum.YES.name());
    if (rpPayProduct == null) {
      throw new PayBizException(PayBizException.PAY_PRODUCT_IS_NOT_EXIST,"?????????????????");
    }
  }
  rpUserPayConfig.setAuditStatus(auditStatus);
  rpUserPayConfig.setEditTime(new Date());
  updateData(rpUserPayConfig);
}
