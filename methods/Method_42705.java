/** 
 * ??????
 * @return
 */
@Override public List<RpUserInfo> listAll(){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("status",PublicStatusEnum.ACTIVE.name());
  return rpUserInfoDao.listBy(paramMap);
}
