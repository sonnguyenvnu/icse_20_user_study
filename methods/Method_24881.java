/** 
 * Dump the list of hidden tokens linked to before the AST node passed in. The only time hidden tokens need to be dumped with this function is when dealing parts of the tree where automatic tree construction was turned off with the ! operator in the grammar file and the nodes were manually constructed in such a way that the usual tokens don't have the necessary hiddenAfter links.
 */
private void dumpHiddenBefore(final AST ast){
  antlr.CommonHiddenStreamToken child=null, parent=((CommonASTWithHiddenTokens)ast).getHiddenBefore();
  if (parent == null) {
    return;
  }
  do {
    child=parent;
    parent=child.getHiddenBefore();
  }
 while (parent != null);
  dumpHiddenTokens(child);
}
