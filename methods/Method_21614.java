public JoinSelect parseJoinSelect(SQLQueryExpr sqlExpr) throws SqlParseException {
  MySqlSelectQueryBlock query=(MySqlSelectQueryBlock)sqlExpr.getSubQuery().getQuery();
  List<From> joinedFrom=findJoinedFrom(query.getFrom());
  if (joinedFrom.size() != 2)   throw new RuntimeException("currently supports only 2 tables join");
  JoinSelect joinSelect=createBasicJoinSelectAccordingToTableSource((SQLJoinTableSource)query.getFrom());
  List<Hint> hints=parseHints(query.getHints());
  joinSelect.setHints(hints);
  String firstTableAlias=joinedFrom.get(0).getAlias();
  String secondTableAlias=joinedFrom.get(1).getAlias();
  Map<String,Where> aliasToWhere=splitAndFindWhere(query.getWhere(),firstTableAlias,secondTableAlias);
  Map<String,List<SQLSelectOrderByItem>> aliasToOrderBy=splitAndFindOrder(query.getOrderBy(),firstTableAlias,secondTableAlias);
  List<Condition> connectedConditions=getConditionsFlatten(joinSelect.getConnectedWhere());
  joinSelect.setConnectedConditions(connectedConditions);
  fillTableSelectedJoin(joinSelect.getFirstTable(),query,joinedFrom.get(0),aliasToWhere.get(firstTableAlias),aliasToOrderBy.get(firstTableAlias),connectedConditions);
  fillTableSelectedJoin(joinSelect.getSecondTable(),query,joinedFrom.get(1),aliasToWhere.get(secondTableAlias),aliasToOrderBy.get(secondTableAlias),connectedConditions);
  updateJoinLimit(query.getLimit(),joinSelect);
  return joinSelect;
}
