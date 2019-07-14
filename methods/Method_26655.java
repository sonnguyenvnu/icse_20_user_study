@Override public JCExpression inline(Inliner inliner){
  return inliner.getBinding(key());
}
