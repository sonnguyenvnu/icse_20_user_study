/** 
 * Check if the node should be ignored, depending on the given flags and the context.
 */
private boolean ignoreNode(Node node,ASTStatement loopBody,IgnoreFlags... ignoreFlags){
  if (ignoreFlags.length == 0) {
    return false;
  }
  final List<IgnoreFlags> ignoreFlagsList=Arrays.asList(ignoreFlags);
  final boolean ignoredFirstStatement=ignoreFlagsList.contains(IgnoreFlags.IGNORE_FIRST) && isFirstStatementInBlock(node,loopBody);
  final boolean ignoredConditional=ignoreFlagsList.contains(IgnoreFlags.IGNORE_CONDITIONAL) && isConditionallyExecuted(node,loopBody);
  return ignoredFirstStatement || ignoredConditional;
}
