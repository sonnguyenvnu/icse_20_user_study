/** 
 * Matches an AST node that is enclosed by some node that matches the given matcher. <p>TODO(eaftan): This could be used instead of enclosingBlock and enclosingClass.
 */
public static Matcher<Tree> enclosingNode(Matcher<Tree> matcher){
  return (t,state) -> {
    TreePath path=state.getPath().getParentPath();
    while (path != null) {
      Tree node=path.getLeaf();
      state=state.withPath(path);
      if (matcher.matches(node,state)) {
        return true;
      }
      path=path.getParentPath();
    }
    return false;
  }
;
}
