/** 
 * Build a query for the reader of the form: SELECT * FROM ks>cf token(pk1,...pkn)>? AND token(pk1,...pkn)<=? [AND user where clauses] [ALLOW FILTERING]
 */
private String buildQuery(){
  fetchKeys();
  List<String> columns=getSelectColumns();
  String selectColumnList=columns.size() == 0 ? "*" : makeColumnList(columns);
  String partitionKeyList=makeColumnList(partitionKeys);
  return String.format("SELECT %s FROM %s.%s WHERE token(%s)>? AND token(%s)<=?" + getAdditionalWhereClauses(),selectColumnList,quote(keyspace),quote(cfName),partitionKeyList,partitionKeyList);
}
