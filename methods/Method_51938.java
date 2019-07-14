/** 
 * Returns the classes in order.
 * @deprecated Use {{@link JavaTypeQualifiedName#getClassList()}. Will be removed in 7.0.0
 */
@Deprecated public String[] getClasses(){
  return getClassName().getClassList().toArray(new String[0]);
}
