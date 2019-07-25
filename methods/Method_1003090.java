/** 
 * Do the search.
 * @param conn the database connection
 * @param text the query
 * @param limit the limit
 * @param offset the offset
 * @param data whether the raw data should be returned
 * @return the result set
 */
protected static ResultSet search(Connection conn,String text,int limit,int offset,boolean data) throws SQLException {
  SimpleResultSet result=createResultSet(data);
  if (conn.getMetaData().getURL().startsWith("jdbc:columnlist:")) {
    return result;
  }
  if (text == null || StringUtils.isWhitespaceOrEmpty(text)) {
    return result;
  }
  FullTextSettings setting=FullTextSettings.getInstance(conn);
  if (!setting.isInitialized()) {
    init(conn);
  }
  Set<String> words=new HashSet<>();
  addWords(setting,words,text);
  Set<Integer> rIds=null, lastRowIds;
  PreparedStatement prepSelectMapByWordId=setting.prepare(conn,SELECT_MAP_BY_WORD_ID);
  for (  String word : words) {
    lastRowIds=rIds;
    rIds=new HashSet<>();
    Integer wId=setting.getWordId(word);
    if (wId == null) {
      continue;
    }
    prepSelectMapByWordId.setInt(1,wId);
    ResultSet rs=prepSelectMapByWordId.executeQuery();
    while (rs.next()) {
      Integer rId=rs.getInt(1);
      if (lastRowIds == null || lastRowIds.contains(rId)) {
        rIds.add(rId);
      }
    }
  }
  if (rIds == null || rIds.isEmpty()) {
    return result;
  }
  PreparedStatement prepSelectRowById=setting.prepare(conn,SELECT_ROW_BY_ID);
  int rowCount=0;
  for (  int rowId : rIds) {
    prepSelectRowById.setInt(1,rowId);
    ResultSet rs=prepSelectRowById.executeQuery();
    if (!rs.next()) {
      continue;
    }
    if (offset > 0) {
      offset--;
    }
 else {
      String key=rs.getString(1);
      int indexId=rs.getInt(2);
      IndexInfo index=setting.getIndexInfo(indexId);
      if (data) {
        Object[][] columnData=parseKey(conn,key);
        result.addRow(index.schema,index.table,columnData[0],columnData[1],1.0);
      }
 else {
        String query=StringUtils.quoteIdentifier(index.schema) + "." + StringUtils.quoteIdentifier(index.table) + " WHERE " + key;
        result.addRow(query,1.0);
      }
      rowCount++;
      if (limit > 0 && rowCount >= limit) {
        break;
      }
    }
  }
  return result;
}
