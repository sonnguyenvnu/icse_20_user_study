/** 
 * Produces an extractor which, if the tree matches, extracts the type of the first argument to the method invocation.
 */
public static TypeExtractor<MethodInvocationTree> extractFirstArg(Matcher<MethodInvocationTree> m){
  return (tree,state) -> {
    if (m.matches(tree,state)) {
      return Optional.ofNullable(ASTHelpers.getType(tree.getArguments().get(0)));
    }
    return Optional.empty();
  }
;
}
