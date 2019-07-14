private void cont(AbstractJavaNode node){
  super.visit(node,null);
  scopes.pop();
}
