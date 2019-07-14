/** 
 * ????????
 */
@Override public List<RpPayWay> listAll(){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("status",PublicStatusEnum.ACTIVE.name());
  return rpPayWayDao.listBy(paramMap);
}
