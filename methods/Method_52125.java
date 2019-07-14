private boolean usedInOuter(NameDeclaration decl,JavaNode body){
  List<ASTClassOrInterfaceBodyDeclaration> classOrInterfaceBodyDeclarations=body.findChildrenOfType(ASTClassOrInterfaceBodyDeclaration.class);
  List<ASTEnumConstant> enumConstants=body.findChildrenOfType(ASTEnumConstant.class);
  List<AbstractJavaNode> nodes=new ArrayList<>();
  nodes.addAll(classOrInterfaceBodyDeclarations);
  nodes.addAll(enumConstants);
  for (  AbstractJavaNode node : nodes) {
    for (    ASTPrimarySuffix primarySuffix : node.findDescendantsOfType(ASTPrimarySuffix.class,true)) {
      if (decl.getImage().equals(primarySuffix.getImage())) {
        return true;
      }
    }
    for (    ASTPrimaryPrefix primaryPrefix : node.findDescendantsOfType(ASTPrimaryPrefix.class,true)) {
      ASTName name=primaryPrefix.getFirstDescendantOfType(ASTName.class);
      if (name != null) {
        for (        String id : name.getImage().split("\\.")) {
          if (id.equals(decl.getImage())) {
            return true;
          }
        }
      }
    }
  }
  return false;
}
