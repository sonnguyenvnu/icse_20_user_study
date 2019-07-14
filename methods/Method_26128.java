/** 
 * Returns true if and only if the given MethodInvocationTree <p>1) is a Stream API invocation, .e.g. map, filter, collect 2) the source of the stream has the same expression representation as streamSourceExpression.
 */
private static boolean isStreamApiInvocationOnStreamSource(@Nullable ExpressionTree rootTree,ExpressionTree streamSourceExpression,VisitorState visitorState){
  ExpressionTree expressionTree=rootTree;
  while (STREAM_API_INVOCATION_MATCHER.matches(expressionTree,visitorState)) {
    expressionTree=ASTHelpers.getReceiver(expressionTree);
  }
  if (!COLLECTION_TO_STREAM_MATCHER.matches(expressionTree,visitorState)) {
    return false;
  }
  return isSameExpression(ASTHelpers.getReceiver(expressionTree),streamSourceExpression);
}
