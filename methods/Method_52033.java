/** 
 * Rollback the context to the state of the enclosing class. 
 */
private void rollbackClassContext(){
  localIndices=localIndices.tail();
  classNames=classNames.tail();
  anonymousCounters.pop();
  lambdaCounters.pop();
  currentLocalIndices.pop();
  innermostEnclosingTypeName.pop();
}
