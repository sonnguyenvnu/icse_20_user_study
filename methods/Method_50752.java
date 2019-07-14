private Set<String> getTypesFromSOQLQuery(final ASTSoqlExpression node){
  final Set<String> retVal=new HashSet<>();
  final String canonQuery=node.getNode().getCanonicalQuery();
  Matcher m=SELECT_FROM_PATTERN.matcher(canonQuery);
  while (m.find()) {
    retVal.add(new StringBuffer().append(node.getNode().getDefiningType().getApexName()).append(":").append(m.group(1)).toString());
  }
  return retVal;
}
