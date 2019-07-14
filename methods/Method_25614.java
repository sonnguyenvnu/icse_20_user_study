@Override public Description matchMethod(MethodTree tree,VisitorState state){
  if (tree.getBody() == null) {
    return NO_MATCH;
  }
  tree.getBody().accept(new TreeScanner<Void,Void>(){
    @Override public Void visitBlock(    BlockTree block,    Void unused){
      Description description=scanBlock(tree,block,state);
      if (description != NO_MATCH) {
        state.reportMatch(description);
      }
      return super.visitBlock(block,unused);
    }
  }
,null);
  return NO_MATCH;
}
