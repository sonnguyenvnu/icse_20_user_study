/** 
 * Produces an extractor which, if the tree matches, extracts the type of the first argument whose type is  {@link Class} (preserving its {@code <T>} type parameter, if it has one}.
 * @param m the matcher to use. It is an error for this matcher to succeed on any Tree that doesnot include at least one argument of type  {@link Class}; if such a matcher is provided, the behavior of the returned Extractor is undefined.
 */
public static TypeExtractor<MethodInvocationTree> extractClassArg(Matcher<MethodInvocationTree> m){
  return (tree,state) -> {
    if (m.matches(tree,state)) {
      for (      ExpressionTree argument : tree.getArguments()) {
        Type argumentType=ASTHelpers.getType(argument);
        if (ASTHelpers.isSameType(argumentType,state.getSymtab().classType,state)) {
          return Optional.of(argumentType);
        }
      }
      throw new IllegalStateException();
    }
    return Optional.empty();
  }
;
}
