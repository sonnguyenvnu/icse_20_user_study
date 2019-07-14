/** 
 * Extracts references for given property. Returns  {@code null} if property is not marked with anannotation.
 */
public BeanReferences readReferenceFromAnnotation(final PropertyDescriptor propertyDescriptor){
  final MethodDescriptor writeMethodDescriptor=propertyDescriptor.getWriteMethodDescriptor();
  final FieldDescriptor fieldDescriptor=propertyDescriptor.getFieldDescriptor();
  PetiteInject ref=null;
  if (writeMethodDescriptor != null) {
    ref=writeMethodDescriptor.getMethod().getAnnotation(PetiteInject.class);
  }
  if (ref == null && fieldDescriptor != null) {
    ref=fieldDescriptor.getField().getAnnotation(PetiteInject.class);
  }
  if (ref == null) {
    return null;
  }
  BeanReferences reference=null;
  String name=ref.value().trim();
  if (name.length() != 0) {
    reference=BeanReferences.of(name);
  }
  reference=updateReferencesWithDefaultsIfNeeded(propertyDescriptor,reference);
  reference=reference.removeDuplicateNames();
  return reference;
}
