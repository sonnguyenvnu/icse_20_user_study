/** 
 * Returns a string describing the exception type caught by the given try tree's catch statement(s), defaulting to  {@code "Exception"} if more than one exception type is caught.
 */
private static String exceptionToString(TryTree tree,VisitorState state){
  if (tree.getCatches().size() != 1) {
    return "Exception";
  }
  Tree exceptionType=tree.getCatches().iterator().next().getParameter().getType();
  Type type=ASTHelpers.getType(exceptionType);
  if (type != null && type.isUnion()) {
    return "Exception";
  }
  return state.getSourceForNode(exceptionType);
}
