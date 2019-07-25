protected Page<Map<String,Object>> find(Criteria.ResultMappedCriteria resultMapped,Connection conn){
  List<Object> valueList=resultMapped.getValueList();
  String[] sqlArr=this.criteriaParser.parse(resultMapped);
  String sqlCount=sqlArr[0];
  String sql=sqlArr[1];
  int page=resultMapped.getPage();
  int rows=resultMapped.getRows();
  int start=(page - 1) * rows;
  sql=dialect.match(sql,start,rows);
  if (ConfigAdapter.isIsShowSql())   System.out.println(sql);
  Page<Map<String,Object>> pagination=new Page<Map<String,Object>>();
  pagination.setClz(Map.class);
  pagination.setPage(page == 0 ? 1 : page);
  pagination.setRows(rows == 0 ? Integer.MAX_VALUE : rows);
  pagination.setSortList(resultMapped.getSortList());
  pagination.setScroll(resultMapped.isScroll());
  List<Map<String,Object>> list=pagination.getList();
  PreparedStatement pstmt=null;
  try {
    conn.setAutoCommit(true);
    pstmt=conn.prepareStatement(sql);
    int i=1;
    for (    Object value : valueList) {
      value=this.dialect.filterValue(value);
      this.dialect.setObject(i++,value,pstmt);
    }
    ResultSet rs=pstmt.executeQuery();
    if (rs != null) {
      List<String> resultKeyList=resultMapped.getResultKeyList();
      if (resultKeyList.isEmpty()) {
        resultKeyList=resultMapped.listAllResultKey();
      }
      while (rs.next()) {
        Map<String,Object> mapR=new HashMap<String,Object>();
        list.add(mapR);
        for (        String property : resultKeyList) {
          String mapper=resultMapped.getMapMapper().mapper(property);
          Object obj=this.dialect.mappedResult(property,mapper,resultMapped.getAliaMap(),rs);
          mapR.put(property,obj);
        }
      }
      ResultSortUtil.sort(list,resultMapped);
      long count=0;
      if (!resultMapped.isScroll()) {
        int size=pagination.getList().size();
        if (page == 0) {
          count=size;
        }
 else         if (size > 0) {
          count=getCount(sqlCount,valueList);
        }
        pagination.setTotalRows(count);
      }
      String resultKey0=resultKeyList.get(0);
      if (!resultKey0.contains("."))       return pagination;
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
 finally {
    close(pstmt);
    close(conn);
  }
  List<Map<String,Object>> stringKeyMapList=pagination.getList();
  if (!stringKeyMapList.isEmpty()) {
    List<Map<String,Object>> jsonableMapList=BeanMapUtil.toJsonableMapList(stringKeyMapList);
    pagination.reSetList(jsonableMapList);
  }
  return pagination;
}
