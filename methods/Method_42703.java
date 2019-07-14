/** 
 * ????????????
 * @param merchantNo
 * @return
 */
@Override public RpUserInfo getDataByMerchentNo(String merchantNo){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("userNo",merchantNo);
  paramMap.put("status",PublicStatusEnum.ACTIVE.name());
  return rpUserInfoDao.getBy(paramMap);
}
