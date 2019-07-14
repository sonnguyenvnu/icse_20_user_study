private List<Condition> getJoinConditionsFlatten(SQLJoinTableSource from) throws SqlParseException {
  List<Condition> conditions=new ArrayList<>();
  if (from.getCondition() == null)   return conditions;
  Where where=Where.newInstance();
  WhereParser whereParser=new WhereParser(this,from.getCondition());
  whereParser.parseWhere(from.getCondition(),where);
  addIfConditionRecursive(where,conditions);
  return conditions;
}
