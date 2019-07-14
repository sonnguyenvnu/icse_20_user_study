/** 
 * ???????????
 * @param mobile
 * @return
 */
@Override public RpUserInfo getDataByMobile(String mobile){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("mobile",mobile);
  paramMap.put("status",PublicStatusEnum.ACTIVE.name());
  return rpUserInfoDao.getBy(paramMap);
}
