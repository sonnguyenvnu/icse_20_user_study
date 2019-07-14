/** 
 * Defines class as a target.
 */
public T setTarget(final Class target){
  assertTargetIsNotDefined();
  try {
    targetInputStream=ClassLoaderUtil.getClassAsStream(target);
    if (targetInputStream == null) {
      throw new ProxettaException("Target class not found: " + target.getName());
    }
    targetClass=target;
    targetClassName=target.getName();
  }
 catch (  IOException ioex) {
    StreamUtil.close(targetInputStream);
    throw new ProxettaException("Unable to stream class: " + target.getName(),ioex);
  }
  return _this();
}
