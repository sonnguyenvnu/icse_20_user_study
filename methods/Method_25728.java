/** 
 * Given an  {@link ExpressionTree} that represents an argument of array type, rewrites it to wrapit in a call to either  {@link java.util.Arrays#hashCode} if it is single dimensional, or {@link java.util.Arrays#deepHashCode} if it is multidimensional.
 */
private static String rewriteArrayArgument(ExpressionTree arg,VisitorState state){
  Types types=state.getTypes();
  Type argType=ASTHelpers.getType(arg);
  Preconditions.checkState(types.isArray(argType),"arg must be of array type");
  if (types.isArray(types.elemtype(argType))) {
    return "Arrays.deepHashCode(" + state.getSourceForNode(arg) + ")";
  }
 else {
    return "Arrays.hashCode(" + state.getSourceForNode(arg) + ")";
  }
}
