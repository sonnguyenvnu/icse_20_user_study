private ScriptSortBuilder.ScriptSortType judgeIsStringSort(SQLExpr expr){
  if (expr instanceof SQLCaseExpr) {
    List<SQLCaseExpr.Item> itemList=((SQLCaseExpr)expr).getItems();
    for (    SQLCaseExpr.Item item : itemList) {
      if (item.getValueExpr() instanceof SQLCharExpr) {
        return ScriptSortBuilder.ScriptSortType.STRING;
      }
    }
  }
  return ScriptSortBuilder.ScriptSortType.NUMBER;
}
