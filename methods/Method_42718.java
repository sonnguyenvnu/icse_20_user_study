/** 
 * ????key??????????
 * @param payKey
 * @return
 */
public RpUserPayConfig getByPayKey(String payKey){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("payKey",payKey);
  paramMap.put("status",PublicStatusEnum.ACTIVE.name());
  paramMap.put("auditStatus",PublicEnum.YES.name());
  return rpUserPayConfigDao.getBy(paramMap);
}
