@Override protected List<ASTMethod> findOperations(ASTUserClassOrInterface<?> node){
  List<ASTMethod> candidates=node.findChildrenOfType(ASTMethod.class);
  List<ASTMethod> result=new ArrayList<>(candidates);
  for (  ASTMethod method : candidates) {
    if (method.getImage().matches("(<clinit>|<init>|clone)")) {
      result.remove(method);
    }
  }
  return result;
}
