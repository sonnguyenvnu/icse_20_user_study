@Override public PageBean listPage(PageParam pageParam,RpPayProduct rpPayProduct){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("status",PublicStatusEnum.ACTIVE.name());
  paramMap.put("auditStatus",rpPayProduct.getAuditStatus());
  paramMap.put("productName",rpPayProduct.getProductName());
  return rpPayProductDao.listPage(pageParam,paramMap);
}
