@Override public UVariableDecl visitVariable(VariableTree tree,Void v){
  return UVariableDecl.create(tree.getName(),templateType(tree.getType()),(tree.getInitializer() == null) ? null : template(tree.getInitializer()));
}
