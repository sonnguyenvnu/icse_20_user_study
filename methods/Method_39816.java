/** 
 * Defines class name as a target. Class will not be loaded by classloader!
 */
protected T setTarget(final String targetName){
  assertTargetIsNotDefined();
  try {
    targetInputStream=ClassLoaderUtil.getClassAsStream(targetName);
    if (targetInputStream == null) {
      throw new ProxettaException("Target class not found: " + targetName);
    }
    targetClassName=targetName;
    targetClass=null;
  }
 catch (  IOException ioex) {
    StreamUtil.close(targetInputStream);
    throw new ProxettaException("Unable to get stream class name: " + targetName,ioex);
  }
  return _this();
}
