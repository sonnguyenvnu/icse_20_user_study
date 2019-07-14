@Override public PageBean listPage(PageParam pageParam,RpUserPayInfo rpUserPayInfo){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  return rpUserPayInfoDao.listPage(pageParam,paramMap);
}
