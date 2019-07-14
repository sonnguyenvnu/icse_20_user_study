private void addOrderByToSelect(Select select,List<SQLSelectOrderByItem> items,String alias) throws SqlParseException {
  for (  SQLSelectOrderByItem sqlSelectOrderByItem : items) {
    SQLExpr expr=sqlSelectOrderByItem.getExpr();
    Field f=FieldMaker.makeField(expr,null,null);
    String orderByName=f.toString();
    if (sqlSelectOrderByItem.getType() == null) {
      sqlSelectOrderByItem.setType(SQLOrderingSpecification.ASC);
    }
    String type=sqlSelectOrderByItem.getType().toString();
    orderByName=orderByName.replace("`","");
    if (alias != null)     orderByName=orderByName.replaceFirst(alias + "\\.","");
    ScriptSortBuilder.ScriptSortType scriptSortType=judgeIsStringSort(expr);
    select.addOrderBy(f.getNestedPath(),orderByName,type,scriptSortType);
  }
}
