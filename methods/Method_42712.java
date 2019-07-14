/** 
 * ?????????????
 */
@Override public List<RpUserPayConfig> listByProductCode(String productCode){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("productCode",productCode);
  paramMap.put("status",PublicStatusEnum.ACTIVE.name());
  paramMap.put("auditStatus",PublicEnum.YES.name());
  return rpUserPayConfigDao.listBy(paramMap);
}
