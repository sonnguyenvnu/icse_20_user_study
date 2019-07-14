private String getVariableName(ASTPrimaryExpression node){
  ASTPrimaryPrefix prefix=node.getFirstDescendantOfType(ASTPrimaryPrefix.class);
  if (prefix.usesThisModifier()) {
    List<ASTPrimarySuffix> suffixes=node.findChildrenOfType(ASTPrimarySuffix.class);
    if (suffixes.size() > 1) {
      if (!suffixes.get(1).isArguments()) {
        return suffixes.get(0).getImage();
      }
    }
  }
  ASTName name=prefix.getFirstDescendantOfType(ASTName.class);
  String variableName=null;
  if (name != null) {
    int dotIndex=name.getImage().indexOf(".");
    if (dotIndex == -1) {
      variableName=name.getImage();
    }
 else {
      variableName=name.getImage().substring(0,dotIndex);
    }
  }
  return variableName;
}
