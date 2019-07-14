private void findSelect(MySqlSelectQueryBlock query,Select select,String tableAlias) throws SqlParseException {
  List<SQLSelectItem> selectList=query.getSelectList();
  for (  SQLSelectItem sqlSelectItem : selectList) {
    Field field=FieldMaker.makeField(sqlSelectItem.getExpr(),sqlSelectItem.getAlias(),tableAlias);
    select.addField(field);
  }
}
