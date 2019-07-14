private Map<String,Object> newKeyValues(List<Expression> expressions){
  Map<String,Object> keyValues=new HashMap<>();
  for (int i=0; i < columns.size(); i++) {
    columns.get(i).setTable(table);
    if (primaryKeys.contains(columns.get(i).getFullyQualifiedName())) {
      Object expression=expressions.get(i).getASTNode().jjtGetValue();
      keyValues.put(columns.get(i).getFullyQualifiedName(),Reflection.invokeN(expression.getClass(),"getValue",expression,new Object[0]));
    }
  }
  return keyValues;
}
