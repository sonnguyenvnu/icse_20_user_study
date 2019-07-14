/** 
 * Defines class input stream as a target.
 */
protected T setTarget(final InputStream target){
  assertTargetIsNotDefined();
  targetInputStream=target;
  targetClass=null;
  targetClassName=null;
  return _this();
}
