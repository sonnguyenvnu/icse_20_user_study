@SuppressWarnings("TreeToString") private static CharSequence toString(JCTree tree,VisitorState state){
  CharSequence source=state.getSourceForNode(tree);
  return (source == null) ? tree.toString() : source;
}
