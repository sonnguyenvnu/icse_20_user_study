/** 
 * @ APP JOIN ???????? childMap
 * @param config
 * @param resultList
 * @param childMap
 * @throws Exception 
 */
protected void executeAppJoin(SQLConfig config,List<JSONObject> resultList,Map<String,JSONObject> childMap) throws Exception {
  List<Join> joinList=config.getJoinList();
  if (joinList != null) {
    SQLConfig jc;
    SQLConfig cc;
    for (    Join j : joinList) {
      if (j.isAppJoin() == false) {
        Log.i(TAG,"executeAppJoin  for (Join j : joinList) >> j.isAppJoin() == false >>  continue;");
        continue;
      }
      jc=j.getJoinConfig();
      cc=j.getCacheConfig();
      List<Object> targetValueList=new ArrayList<>();
      JSONObject mainTable;
      Object targetValue;
      for (int i=0; i < resultList.size(); i++) {
        mainTable=resultList.get(i);
        targetValue=mainTable == null ? null : mainTable.get(j.getTargetKey());
        if (targetValue != null && targetValueList.contains(targetValue) == false) {
          targetValueList.add(targetValue);
        }
      }
      jc.putWhere(j.getOriginKey(),null,false);
      jc.putWhere(j.getKey() + "{}",targetValueList,false);
      jc.setMain(true).setPreparedValueList(new ArrayList<>());
      boolean prepared=jc.isPrepared();
      final String sql=jc.getSQL(false);
      jc.setPrepared(prepared);
      if (StringUtil.isEmpty(sql,true)) {
        throw new NullPointerException(TAG + ".executeAppJoin  StringUtil.isEmpty(sql, true) >> return null;");
      }
      long startTime=System.currentTimeMillis();
      Log.d(TAG,"\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" + "\n executeAppJoin  startTime = " + startTime + "\n sql = \n " + sql + "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
      ResultSet rs=executeQuery(jc);
      int index=-1;
      ResultSetMetaData rsmd=rs.getMetaData();
      final int length=rsmd.getColumnCount();
      JSONObject result;
      String cacheSql;
      while (rs.next()) {
        index++;
        Log.d(TAG,"\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n executeAppJoin while (rs.next()){  index = " + index + "\n\n");
        result=new JSONObject(true);
        for (int i=1; i <= length; i++) {
          result=onPutColumn(jc,rs,rsmd,index,result,i,null);
        }
        Log.d(TAG,"\n executeAppJoin  while (rs.next()) { resultList.put( " + index + ", result); " + "\n >>>>>>>>>>>>>>>>>>>>>>>>>>> \n\n");
        cc.putWhere(j.getKey(),result.get(j.getKey()),false);
        cacheSql=cc.getSQL(false);
        childMap.put(cacheSql,result);
        Log.d(TAG,">>> executeAppJoin childMap.put('" + cacheSql + "', result);  childMap.size() = " + childMap.size());
      }
      rs.close();
      long endTime=System.currentTimeMillis();
      Log.d(TAG,"\n\n executeAppJoin  endTime = " + endTime + "; duration = " + (endTime - startTime) + "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
    }
  }
}
