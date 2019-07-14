private <T extends Node>List<T> getDeclarationsOfType(ASTAnyTypeDeclaration node,Class<T> tClass){
  List<T> result=new ArrayList<>();
  List<ASTAnyTypeBodyDeclaration> decls=node.getDeclarations();
  for (  ASTAnyTypeBodyDeclaration decl : decls) {
    if (decl.jjtGetNumChildren() > 0 && tClass.isInstance(decl.jjtGetChild(0))) {
      result.add(tClass.cast(decl.jjtGetChild(0)));
    }
  }
  return result;
}
