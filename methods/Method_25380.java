/** 
 * Matches an AST node if its parent node is matched by the given matcher. For example,  {@code parentNode(kindIs(Kind.RETURN))} would match the {@code this} expression in {@code returnthis;}
 */
public static Matcher<Tree> parentNode(Matcher<Tree> treeMatcher){
  return (tree,state) -> {
    TreePath parent=requireNonNull(state.getPath().getParentPath());
    return treeMatcher.matches(parent.getLeaf(),state.withPath(parent));
  }
;
}
