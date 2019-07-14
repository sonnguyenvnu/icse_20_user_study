private boolean returnsInTryCatchOrAfter(TryTree tree,VisitorState state){
  return RETURN_IN_BLOCK.matches(tree,state) || RETURN_AFTER.matches(tree,state);
}
