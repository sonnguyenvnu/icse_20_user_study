/** 
 * Produces an extractor which, if the tree matches, extracts the type of that tree, as given by {@link ASTHelpers#getType(Tree)}.
 */
public static <T extends Tree>TypeExtractor<T> extractType(Matcher<T> m){
  return (tree,state) -> {
    if (m.matches(tree,state)) {
      return Optional.ofNullable(ASTHelpers.getType(tree));
    }
    return Optional.empty();
  }
;
}
