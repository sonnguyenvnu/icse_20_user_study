@Override public PageBean listPage(PageParam pageParam,RpUserInfo rpUserInfo){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("userNo",rpUserInfo.getUserNo());
  return rpUserInfoDao.listPage(pageParam,paramMap);
}
