/** 
 * Get the simple name of the class.
 * @deprecated Use {@link JavaTypeQualifiedName#getClassSimpleName()}. Will be removed in 7.0.0
 */
@Deprecated public String getClassSimpleName(){
  return getClassName().getClassSimpleName();
}
