/** 
 * Returns whether the given  {@code tree} contains any comments in its source. 
 */
public static boolean containsComments(Tree tree,VisitorState state){
  return state.getOffsetTokensForNode(tree).stream().anyMatch(t -> !t.comments().isEmpty());
}
