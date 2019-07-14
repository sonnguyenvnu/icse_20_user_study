@Override public PageBean listPage(PageParam pageParam,RpUserPayConfig rpUserPayConfig){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("productCode",rpUserPayConfig.getProductCode());
  paramMap.put("userNo",rpUserPayConfig.getUserNo());
  paramMap.put("userName",rpUserPayConfig.getUserName());
  paramMap.put("productName",rpUserPayConfig.getProductName());
  paramMap.put("status",PublicStatusEnum.ACTIVE.name());
  return rpUserPayConfigDao.listPage(pageParam,paramMap);
}
