@Override public JavaTypeDefinition getAsSuper(Class<?> superClazz){
  if (Objects.equals(clazz,superClazz)) {
    return this;
  }
  for (  JavaTypeDefinition superTypeDef : getSuperTypeSet()) {
    if (superTypeDef.getType() == superClazz) {
      return superTypeDef;
    }
  }
  return null;
}
