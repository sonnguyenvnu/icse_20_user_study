/** 
 * Helper to create a Description for the common case where there is no fix. 
 */
@CheckReturnValue protected Description describeMatch(Tree node){
  return buildDescription(node).build();
}
