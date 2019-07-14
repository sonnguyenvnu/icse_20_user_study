@Override protected void traverse(Tree tree,VisitorState state){
  ClassTree classTree=state.findEnclosing(ClassTree.class);
  if (classTree.getModifiers().getFlags().contains(Modifier.FINAL)) {
    return;
  }
  ClassSymbol classSym=ASTHelpers.getSymbol(classTree);
  tree.accept(new TreeScanner<Void,Void>(){
    @Override public Void visitMethodInvocation(    MethodInvocationTree node,    Void data){
      MethodSymbol method=ASTHelpers.getSymbol(node);
      if (method != null && !method.isConstructor() && !method.isStatic() && !method.isPrivate() && !method.getModifiers().contains(Modifier.FINAL) && isOnThis(node) && method.isMemberOf(classSym,state.getTypes())) {
        state.reportMatch(describeMatch(node));
      }
      return super.visitMethodInvocation(node,data);
    }
  }
,null);
}
