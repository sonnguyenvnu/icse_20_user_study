/** 
 * {@inheritDoc}
 */
@Override public boolean apply(final MethodInfo methodInfo){
  return methodInfo.hasAnnotation(annotationClasses);
}
