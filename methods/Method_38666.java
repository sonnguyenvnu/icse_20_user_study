/** 
 * Matches property types that are ignored by default.
 */
public boolean matchIgnoredPropertyTypes(final Class propertyType,final boolean excludeMaps,final boolean include){
  if (!include) {
    return false;
  }
  if (propertyType != null) {
    if (!jsonSerializer.deep) {
      ClassDescriptor propertyTypeClassDescriptor=ClassIntrospector.get().lookup(propertyType);
      if (propertyTypeClassDescriptor.isArray()) {
        return false;
      }
      if (propertyTypeClassDescriptor.isCollection()) {
        return false;
      }
      if (excludeMaps) {
        if (propertyTypeClassDescriptor.isMap()) {
          return false;
        }
      }
    }
    if (jsonSerializer.excludedTypes != null) {
      for (      Class excludedType : jsonSerializer.excludedTypes) {
        if (ClassUtil.isTypeOf(propertyType,excludedType)) {
          return false;
        }
      }
    }
    final String propertyTypeName=propertyType.getName();
    if (jsonSerializer.excludedTypeNames != null) {
      for (      String excludedTypeName : jsonSerializer.excludedTypeNames) {
        if (Wildcard.match(propertyTypeName,excludedTypeName)) {
          return false;
        }
      }
    }
  }
  return true;
}
