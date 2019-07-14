private BeanReferences updateReferencesWithDefaultsIfNeeded(final PropertyDescriptor propertyDescriptor,BeanReferences references){
  if (references == null || references.isEmpty()) {
    references=buildDefaultReference(propertyDescriptor);
  }
  return references;
}
