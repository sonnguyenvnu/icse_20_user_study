private void addIfConditionRecursive(Where where,List<Condition> conditions) throws SqlParseException {
  if (where instanceof Condition) {
    Condition cond=(Condition)where;
    if (!((cond.getValue() instanceof SQLIdentifierExpr) || (cond.getValue() instanceof SQLPropertyExpr) || (cond.getValue() instanceof String))) {
      throw new SqlParseException("conditions on join should be one side is secondTable OPEAR firstTable, condition was:" + cond.toString());
    }
    conditions.add(cond);
  }
  for (  Where innerWhere : where.getWheres()) {
    addIfConditionRecursive(innerWhere,conditions);
  }
}
