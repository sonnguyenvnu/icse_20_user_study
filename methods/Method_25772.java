@Override public void visitNewClass(JCTree.JCNewClass tree){
  super.visitNewClass(tree);
  Type type=ASTHelpers.getType(tree.clazz);
  if (type == null) {
    return;
  }
  if (memberOfEnclosing(owner,state,type.tsym)) {
    canPossiblyBeStatic=false;
  }
}
