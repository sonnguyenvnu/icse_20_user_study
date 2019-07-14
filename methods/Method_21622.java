private List<Condition> getConditionsFlatten(Where where) throws SqlParseException {
  List<Condition> conditions=new ArrayList<>();
  if (where == null)   return conditions;
  addIfConditionRecursive(where,conditions);
  return conditions;
}
