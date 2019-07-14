@Override public PageBean listPage(PageParam pageParam,RpPayWay rpPayWay){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("status",PublicStatusEnum.ACTIVE.name());
  paramMap.put("payProductCode",rpPayWay.getPayProductCode());
  paramMap.put("payWayName",rpPayWay.getPayWayName());
  paramMap.put("payTypeName",rpPayWay.getPayTypeName());
  return rpPayWayDao.listPage(pageParam,paramMap);
}
