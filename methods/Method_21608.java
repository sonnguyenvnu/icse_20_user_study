private String sameAliasWhere(Where where,String... aliases) throws SqlParseException {
  if (where == null)   return null;
  if (where instanceof Condition) {
    Condition condition=(Condition)where;
    String fieldName=condition.getName();
    for (    String alias : aliases) {
      String prefix=alias + ".";
      if (fieldName.startsWith(prefix)) {
        return alias;
      }
    }
    throw new SqlParseException(String.format("fieldName : %s on codition:%s does not contain alias",fieldName,condition.toString()));
  }
  List<String> sameAliases=new ArrayList<>();
  if (where.getWheres() != null && where.getWheres().size() > 0) {
    for (    Where innerWhere : where.getWheres())     sameAliases.add(sameAliasWhere(innerWhere,aliases));
  }
  if (sameAliases.contains(null))   return null;
  String firstAlias=sameAliases.get(0);
  for (  String alias : sameAliases) {
    if (!alias.equals(firstAlias))     return null;
  }
  return firstAlias;
}
