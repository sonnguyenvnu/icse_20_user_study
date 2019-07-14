@Override protected Set<JavaTypeDefinition> getSuperTypeSet(Set<JavaTypeDefinition> destinationSet){
  destinationSet.add(this);
  if (this.clazz != Object.class) {
    resolveTypeDefinition(clazz.getGenericSuperclass()).getSuperTypeSet(destinationSet);
    for (    Type type : clazz.getGenericInterfaces()) {
      resolveTypeDefinition(type).getSuperTypeSet(destinationSet);
    }
  }
  return destinationSet;
}
