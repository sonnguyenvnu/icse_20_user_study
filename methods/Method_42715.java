/** 
 * ??????
 * @param userNo
 */
@Override public void deleteUserPayConfig(String userNo) throws PayBizException {
  RpUserPayConfig rpUserPayConfig=rpUserPayConfigDao.getByUserNo(userNo,null);
  if (rpUserPayConfig == null) {
    throw new PayBizException(PayBizException.USER_PAY_CONFIG_IS_NOT_EXIST,"?????????");
  }
  rpUserPayConfig.setStatus(PublicStatusEnum.UNACTIVE.name());
  rpUserPayConfig.setEditTime(new Date());
  updateData(rpUserPayConfig);
}
