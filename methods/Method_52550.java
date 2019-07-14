public boolean equivalent(JavaTypeDefinition def){
  return clazz.equals(def.getType()) && getTypeParameterCount() == def.getTypeParameterCount();
}
