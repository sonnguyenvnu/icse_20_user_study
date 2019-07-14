/** 
 * @deprecated Use {@link QualifiedNameFactory#ofClass(Class)}. Will be removed in 7.0.0
 */
@Deprecated public static JavaQualifiedName ofClass(Class<?> clazz){
  return QualifiedNameFactory.ofClass(clazz);
}
