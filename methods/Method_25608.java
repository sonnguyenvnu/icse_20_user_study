/** 
 * Returns the list of  {@link Token}s for the given  {@link JCTree}, offset by the start position of the tree within the overall source. <p>This is moderately expensive (the source of the node has to be re-lexed), so it should only be used if a fix is already going to be emitted.
 */
public List<ErrorProneToken> getOffsetTokensForNode(Tree tree){
  int start=((JCTree)tree).getStartPosition();
  return ErrorProneTokens.getTokens(getSourceForNode(tree),start,context);
}
