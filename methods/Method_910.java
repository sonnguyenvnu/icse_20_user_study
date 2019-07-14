private Set<String> getSqlMapFields(List<Node> fieldDeclarations){
  if (fieldDeclarations == null || fieldDeclarations.isEmpty()) {
    return Collections.emptySet();
  }
  Set<String> set=new HashSet<>();
  for (  Node node : fieldDeclarations) {
    ASTFieldDeclaration fieldDeclaration=(ASTFieldDeclaration)node;
    if (sqlMapClientField(fieldDeclaration)) {
      set.add(VariableUtils.getVariableName(fieldDeclaration));
    }
  }
  return set;
}
