/** 
 * Helper to create a Description for the common case where there is a fix. 
 */
@CheckReturnValue protected Description describeMatch(JCTree node,Fix fix){
  return describeMatch((Tree)node,fix);
}
