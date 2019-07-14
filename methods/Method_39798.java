/** 
 * Returns <code>true</code> if method has a return type.
 */
default boolean hasReturnValue(){
  return getReturnType().getOpcode() != AsmUtil.TYPE_VOID;
}
