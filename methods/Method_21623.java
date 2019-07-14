private Map<String,Where> splitWheres(Where where,String... aliases) throws SqlParseException {
  Map<String,Where> aliasToWhere=new HashMap<>();
  for (  String alias : aliases) {
    aliasToWhere.put(alias,null);
  }
  if (where == null)   return aliasToWhere;
  String allWhereFromSameAlias=sameAliasWhere(where,aliases);
  if (allWhereFromSameAlias != null) {
    removeAliasPrefix(where,allWhereFromSameAlias);
    aliasToWhere.put(allWhereFromSameAlias,where);
    return aliasToWhere;
  }
  for (  Where innerWhere : where.getWheres()) {
    String sameAlias=sameAliasWhere(innerWhere,aliases);
    if (sameAlias == null)     throw new SqlParseException("Currently support only one hierarchy on different tables where");
    removeAliasPrefix(innerWhere,sameAlias);
    Where aliasCurrentWhere=aliasToWhere.get(sameAlias);
    if (aliasCurrentWhere == null) {
      aliasToWhere.put(sameAlias,innerWhere);
    }
 else {
      Where andWhereContainer=Where.newInstance();
      andWhereContainer.addWhere(aliasCurrentWhere);
      andWhereContainer.addWhere(innerWhere);
      aliasToWhere.put(sameAlias,andWhereContainer);
    }
  }
  return aliasToWhere;
}
