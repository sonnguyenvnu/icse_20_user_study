/** 
 * Helper to create a Description for the common case where there is a fix. 
 */
@CheckReturnValue protected Description describeMatch(Tree node,Fix fix){
  return buildDescription(node).addFix(fix).build();
}
