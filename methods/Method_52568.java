@Override protected Set<JavaTypeDefinition> getSuperTypeSet(Set<JavaTypeDefinition> destinationSet){
  return firstJavaType().getSuperTypeSet(destinationSet);
}
