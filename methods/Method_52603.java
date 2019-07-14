/** 
 * Merge two types of the same class to something both can be assigned to and is most specific.
 */
public static JavaTypeDefinition merge(JavaTypeDefinition first,JavaTypeDefinition second){
  if (first.getType() != second.getType()) {
    throw new IllegalStateException("Must be called with typedefinitions of the same class");
  }
  if (!first.isGeneric()) {
    return first;
  }
  JavaTypeDefinition[] mergedGeneric=new JavaTypeDefinition[first.getTypeParameterCount()];
  for (int i=0; i < first.getTypeParameterCount(); ++i) {
    if (MethodTypeResolution.isSubtypeable(first.getGenericType(i),second.getGenericType(i))) {
      mergedGeneric[i]=first.getGenericType(i);
    }
 else     if (MethodTypeResolution.isSubtypeable(second.getGenericType(i),first.getGenericType(i))) {
      mergedGeneric[i]=second.getGenericType(i);
    }
 else {
      return JavaTypeDefinition.forClass(Object.class);
    }
  }
  return JavaTypeDefinition.forClass(first.getType(),mergedGeneric);
}
