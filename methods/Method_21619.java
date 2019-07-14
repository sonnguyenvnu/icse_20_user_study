private List<Field> getConnectedFields(List<Condition> conditions,String alias) throws SqlParseException {
  List<Field> fields=new ArrayList<>();
  String prefix=alias + ".";
  for (  Condition condition : conditions) {
    if (condition.getName().startsWith(prefix)) {
      fields.add(new Field(condition.getName().replaceFirst(prefix,""),null));
    }
 else {
      if (!((condition.getValue() instanceof SQLPropertyExpr) || (condition.getValue() instanceof SQLIdentifierExpr) || (condition.getValue() instanceof String))) {
        throw new SqlParseException("conditions on join should be one side is firstTable second Other , condition was:" + condition.toString());
      }
      String aliasDotValue=condition.getValue().toString();
      int indexOfDot=aliasDotValue.indexOf(".");
      String owner=aliasDotValue.substring(0,indexOfDot);
      if (owner.equals(alias))       fields.add(new Field(aliasDotValue.substring(indexOfDot + 1),null));
    }
  }
  return fields;
}
