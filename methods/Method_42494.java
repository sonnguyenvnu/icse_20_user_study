/** 
 * ?????? .
 */
public PageBean listPage(PageParam pageParam,Map<String,Object> paramMap){
  if (paramMap == null) {
    paramMap=new HashMap<String,Object>();
  }
  Long totalCount=sessionTemplate.selectOne(getStatement(SQL_LIST_PAGE_COUNT),paramMap);
  int currentPage=PageBean.checkCurrentPage(totalCount.intValue(),pageParam.getNumPerPage(),pageParam.getPageNum());
  pageParam.setPageNum(currentPage);
  int numPerPage=PageBean.checkNumPerPage(pageParam.getNumPerPage());
  pageParam.setNumPerPage(numPerPage);
  paramMap.put("pageFirst",(pageParam.getPageNum() - 1) * pageParam.getNumPerPage());
  paramMap.put("pageSize",pageParam.getNumPerPage());
  paramMap.put("startRowNum",(pageParam.getPageNum() - 1) * pageParam.getNumPerPage());
  paramMap.put("endRowNum",pageParam.getPageNum() * pageParam.getNumPerPage());
  List<Object> list=sessionTemplate.selectList(getStatement(SQL_LIST_PAGE),paramMap);
  Object isCount=paramMap.get("isCount");
  if (isCount != null && "1".equals(isCount.toString())) {
    Map<String,Object> countResultMap=sessionTemplate.selectOne(getStatement(SQL_COUNT_BY_PAGE_PARAM),paramMap);
    return new PageBean(pageParam.getPageNum(),pageParam.getNumPerPage(),totalCount.intValue(),list,countResultMap);
  }
 else {
    return new PageBean(pageParam.getPageNum(),pageParam.getNumPerPage(),totalCount.intValue(),list);
  }
}
