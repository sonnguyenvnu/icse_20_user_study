private String getAdditionalWhereClauses(){
  String whereClause="";
  if (StringUtils.isNotEmpty(userDefinedWhereClauses)) {
    whereClause+=" AND " + userDefinedWhereClauses;
  }
  if (StringUtils.isNotEmpty(userDefinedWhereClauses)) {
    whereClause+=" ALLOW FILTERING";
  }
  return whereClause;
}
