/** 
 * ????????????
 */
@Override public List<RpPayWay> listByProductCode(String payProductCode){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("payProductCode",payProductCode);
  paramMap.put("status",PublicStatusEnum.ACTIVE.name());
  return rpPayWayDao.listBy(paramMap);
}
