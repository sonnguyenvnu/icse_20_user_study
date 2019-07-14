/** 
 * ??????????
 */
@Override public List<RpUserPayConfig> listByProductCode(String productCode,String auditStatus){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("productCode",productCode);
  paramMap.put("status",PublicStatusEnum.ACTIVE.name());
  paramMap.put("auditStatus",auditStatus);
  return rpUserPayConfigDao.listBy(paramMap);
}
