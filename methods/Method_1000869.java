static JavaUtilCollectionsConverter converter(int kind,JavaType concreteType,Class<?> rawSuper){
  return new JavaUtilCollectionsConverter(kind,concreteType.findSuperType(rawSuper));
}
