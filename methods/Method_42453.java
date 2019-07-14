/** 
 * ??????
 * @return
 */
@Override public List<RpAccount> listAll(){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("status",PublicStatusEnum.ACTIVE.name());
  return rpAccountDao.listBy(paramMap);
}
