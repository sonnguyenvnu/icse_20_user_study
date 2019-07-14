/** 
 * Returns the list of  {@link Token}s for the given  {@link JCTree}. <p>This is moderately expensive (the source of the node has to be re-lexed), so it should only be used if a fix is already going to be emitted.
 */
public List<ErrorProneToken> getTokensForNode(Tree tree){
  return ErrorProneTokens.getTokens(getSourceForNode(tree),context);
}
