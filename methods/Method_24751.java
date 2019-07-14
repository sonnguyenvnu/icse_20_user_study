public static List<SimpleName> getSimpleNameChildren(ASTNode node){
  List<SimpleName> simpleNames=new ArrayList<>();
  node.accept(new ASTVisitor(){
    @Override public boolean visit(    SimpleName simpleName){
      simpleNames.add(simpleName);
      return super.visit(simpleName);
    }
  }
);
  return simpleNames;
}
