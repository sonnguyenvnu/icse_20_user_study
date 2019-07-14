/** 
 * Pushes a new context for an inner class. 
 */
private void updateClassContext(String className,int localIndex){
  localIndices=localIndices.prepend(localIndex);
  classNames=classNames.prepend(className);
  anonymousCounters.push(new MutableInt(0));
  lambdaCounters.push(new MutableInt(0));
  currentLocalIndices.push(new HashMap<String,Integer>());
  innermostEnclosingTypeName.push(contextClassQName());
}
