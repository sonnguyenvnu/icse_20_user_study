/** 
 * Helper method checks to see if a violation occurred, and adds a RuleViolation if it did
 */
private void checkForViolation(Node node,Object data,int concurrentCount){
  if (concurrentCount > threshold) {
    String[] param={String.valueOf(concurrentCount)};
    addViolation(data,node,param);
  }
}
