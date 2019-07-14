private void orderConditionRecursive(String t1Alias,String t2Alias,Where where){
  if (where == null)   return;
  if (where instanceof Condition) {
    Condition c=(Condition)where;
    if (!c.getName().startsWith(t2Alias + ".") || !c.getValue().toString().startsWith(t1Alias + "."))     throw new RuntimeException("On NestedLoops currently only supported Ordered conditions (t2.field2 OPEAR t1.field1) , badCondition was:" + c);
    c.setName(c.getName().replaceFirst(t2Alias + ".",""));
    c.setValue(c.getValue().toString().replaceFirst(t1Alias + ".",""));
    return;
  }
 else {
    for (    Where innerWhere : where.getWheres())     orderConditionRecursive(t1Alias,t2Alias,innerWhere);
  }
}
