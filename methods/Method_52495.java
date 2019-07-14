private JavaTypeDefinition searchImportedStaticFields(String fieldName){
  if (staticFieldImageToTypeDef.containsKey(fieldName)) {
    return staticFieldImageToTypeDef.get(fieldName);
  }
  for (  JavaTypeDefinition anOnDemandImport : importOnDemandStaticClasses) {
    JavaTypeDefinition typeDef=getFieldType(anOnDemandImport,fieldName,currentAcu.getType());
    if (typeDef != null) {
      staticFieldImageToTypeDef.put(fieldName,typeDef);
      return typeDef;
    }
  }
  return null;
}
