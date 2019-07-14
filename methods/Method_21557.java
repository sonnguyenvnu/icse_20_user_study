private void fillSubQueriesFromWhereRecursive(Where where){
  if (where == null)   return;
  if (where instanceof Condition) {
    Condition condition=(Condition)where;
    if (condition.getValue() instanceof SubQueryExpression) {
      this.subQueries.add((SubQueryExpression)condition.getValue());
      this.containsSubQueries=true;
    }
    if (condition.getValue() instanceof Object[]) {
      for (      Object o : (Object[])condition.getValue()) {
        if (o instanceof SubQueryExpression) {
          this.subQueries.add((SubQueryExpression)o);
          this.containsSubQueries=true;
        }
      }
    }
  }
 else {
    for (    Where innerWhere : where.getWheres())     fillSubQueriesFromWhereRecursive(innerWhere);
  }
}
