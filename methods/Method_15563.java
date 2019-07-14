/** 
 * table.put(rsmd.getColumnLabel(i), rs.getObject(i));
 * @param config 
 * @param rs
 * @param rsmd
 * @param tablePosition ?0??
 * @param table
 * @param columnIndex ?1??
 * @param childMap 
 * @return result
 * @throws Exception
 */
protected JSONObject onPutColumn(@NotNull SQLConfig config,@NotNull ResultSet rs,@NotNull ResultSetMetaData rsmd,final int tablePosition,@NotNull JSONObject table,final int columnIndex,Map<String,JSONObject> childMap) throws Exception {
  if (rsmd.getColumnName(columnIndex).startsWith("_")) {
    Log.i(TAG,"select while (rs.next()){ ..." + " >>  rsmd.getColumnName(i).startsWith(_) >> continue;");
    return table;
  }
  String lable=rsmd.getColumnLabel(columnIndex);
  String childTable=childMap == null ? null : rsmd.getTableName(columnIndex);
  JSONObject finalTable=null;
  String childSql=null;
  SQLConfig childConfig=null;
  if (childTable == null) {
    finalTable=table;
  }
 else {
    List<Join> joinList=config.getJoinList();
    if (joinList != null) {
      for (      Join j : joinList) {
        childConfig=j.isAppJoin() ? null : j.getCacheConfig();
        if (childConfig != null && childTable.equalsIgnoreCase(childConfig.getSQLTable())) {
          childConfig.putWhere(j.getKey(),table.get(j.getTargetKey()),false);
          childSql=childConfig.getSQL(false);
          if (StringUtil.isEmpty(childSql,true)) {
            return table;
          }
          finalTable=(JSONObject)childMap.get(childSql);
          break;
        }
      }
    }
    if (finalTable == null) {
      finalTable=new JSONObject(true);
      childMap.put(childSql,finalTable);
    }
  }
  finalTable.put(lable,getValue(config,rs,rsmd,tablePosition,table,columnIndex,childMap));
  return table;
}
