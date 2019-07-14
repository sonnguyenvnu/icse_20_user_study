/** 
 * Returns <code>true</code> if method's return type is <code>void</code>.
 */
default boolean hasNoReturnValue(){
  return getReturnType().getOpcode() == AsmUtil.TYPE_VOID;
}
