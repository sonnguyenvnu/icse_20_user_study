/** 
 * Returns <code>true</code> if method is declared in <code>Object</code> class (root class).
 */
default boolean isRootMethod(){
  return AsmUtil.SIGNATURE_JAVA_LANG_OBJECT.equals(getDeclaredClassName());
}
