private void fillBasicTableSelectJoin(TableOnJoinSelect select,From from,Where where,List<SQLSelectOrderByItem> orderBys,MySqlSelectQueryBlock query) throws SqlParseException {
  select.getFrom().add(from);
  findSelect(query,select,from.getAlias());
  select.setWhere(where);
  addOrderByToSelect(select,orderBys,from.getAlias());
}
