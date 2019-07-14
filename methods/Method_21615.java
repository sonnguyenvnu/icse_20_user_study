private Map<String,List<SQLSelectOrderByItem>> splitAndFindOrder(SQLOrderBy orderBy,String firstTableAlias,String secondTableAlias) throws SqlParseException {
  Map<String,List<SQLSelectOrderByItem>> aliasToOrderBys=new HashMap<>();
  aliasToOrderBys.put(firstTableAlias,new ArrayList<SQLSelectOrderByItem>());
  aliasToOrderBys.put(secondTableAlias,new ArrayList<SQLSelectOrderByItem>());
  if (orderBy == null)   return aliasToOrderBys;
  List<SQLSelectOrderByItem> orderByItems=orderBy.getItems();
  for (  SQLSelectOrderByItem orderByItem : orderByItems) {
    if (orderByItem.getExpr().toString().startsWith(firstTableAlias + ".")) {
      aliasToOrderBys.get(firstTableAlias).add(orderByItem);
    }
 else     if (orderByItem.getExpr().toString().startsWith(secondTableAlias + ".")) {
      aliasToOrderBys.get(secondTableAlias).add(orderByItem);
    }
 else     throw new SqlParseException("order by field on join request should have alias before, got " + orderByItem.getExpr().toString());
  }
  return aliasToOrderBys;
}
