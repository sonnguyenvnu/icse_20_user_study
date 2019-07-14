private static TypeName getTypeUsedInDeclaredMethod(TypeName typeName){
  if (typeName instanceof TypeVariableName) {
    final List<TypeName> bounds=((TypeVariableName)typeName).bounds;
    if (bounds.isEmpty()) {
      return TypeName.OBJECT;
    }
 else {
      return getBaseType(bounds.get(0));
    }
  }
  return getBaseType(typeName);
}
