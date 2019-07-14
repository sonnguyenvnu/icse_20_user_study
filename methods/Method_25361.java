@Override public boolean matches(T statement,VisitorState state){
  BlockTree block=state.findEnclosing(BlockTree.class);
  return block != null && Iterables.getLast(block.getStatements()).equals(statement);
}
