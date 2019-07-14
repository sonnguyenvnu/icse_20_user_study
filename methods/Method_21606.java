private void findGroupBy(MySqlSelectQueryBlock query,Select select) throws SqlParseException {
  SQLSelectGroupByClause groupBy=query.getGroupBy();
  SQLTableSource sqlTableSource=query.getFrom();
  if (groupBy == null) {
    return;
  }
  List<SQLExpr> items=groupBy.getItems();
  List<SQLExpr> standardGroupBys=new ArrayList<>();
  for (  SQLExpr sqlExpr : items) {
    if (sqlExpr instanceof MySqlOrderingExpr) {
      MySqlOrderingExpr sqlSelectGroupByExpr=(MySqlOrderingExpr)sqlExpr;
      sqlExpr=sqlSelectGroupByExpr.getExpr();
    }
    if ((sqlExpr instanceof SQLParensIdentifierExpr || !(sqlExpr instanceof SQLIdentifierExpr || sqlExpr instanceof SQLMethodInvokeExpr)) && !standardGroupBys.isEmpty()) {
      select.addGroupBy(convertExprsToFields(standardGroupBys,sqlTableSource));
      standardGroupBys=new ArrayList<>();
    }
    if (sqlExpr instanceof SQLParensIdentifierExpr) {
      select.addGroupBy(FieldMaker.makeField(((SQLParensIdentifierExpr)sqlExpr).getExpr(),null,sqlTableSource.getAlias()));
    }
 else     if (sqlExpr instanceof SQLListExpr) {
      SQLListExpr listExpr=(SQLListExpr)sqlExpr;
      select.addGroupBy(convertExprsToFields(listExpr.getItems(),sqlTableSource));
    }
 else {
      standardGroupBys.add(sqlExpr);
    }
  }
  if (!standardGroupBys.isEmpty()) {
    select.addGroupBy(convertExprsToFields(standardGroupBys,sqlTableSource));
  }
}
