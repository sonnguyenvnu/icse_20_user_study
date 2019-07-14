private boolean isAStringBuilderBuffer(AbstractJavaNode node,String name){
  Map<VariableNameDeclaration,List<NameOccurrence>> declarations=node.getScope().getDeclarations(VariableNameDeclaration.class);
  for (  VariableNameDeclaration decl : declarations.keySet()) {
    if (decl.getName().equals(name) && TypeHelper.isExactlyAny(decl,StringBuilder.class,StringBuffer.class)) {
      return true;
    }
  }
  return false;
}
