/** 
 * Resolves reference from given values. Returns bean reference of given value or defaults if given name is blank.
 */
public BeanReferences resolveReferenceFromValue(final PropertyDescriptor propertyDescriptor,final String refName){
  BeanReferences references;
  if (refName == null || refName.isEmpty()) {
    references=buildDefaultReference(propertyDescriptor);
  }
 else {
    references=BeanReferences.of(refName);
  }
  references=references.removeDuplicateNames();
  return references;
}
