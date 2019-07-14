/** 
 * Returns  {@code true} if given parameter references is not set.
 */
private boolean parameterReferenceIsNotSet(final BeanReferences parameterReferences){
  if (parameterReferences == null) {
    return true;
  }
  return parameterReferences.isEmpty();
}
