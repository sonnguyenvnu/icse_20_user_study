private static ImmutableList<String> getImmutableSuperTypesForClassType(ClassType classType){
  ImmutableList.Builder<String> immutableSuperTypes=ImmutableList.builder();
  ClassType superType=classType;
  while (superType.supertype_field instanceof ClassType) {
    if (ImmutableCollections.isImmutableType(superType)) {
      immutableSuperTypes.add(getTypeQualifiedName(superType.asElement().type));
    }
    superType=(ClassType)superType.supertype_field;
  }
  return immutableSuperTypes.build();
}
