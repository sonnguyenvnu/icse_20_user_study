private TypeReference getMostOuterTypeRefBySlowLookuping(TypeReference typeRef){
  String name=typeRef.getName();
  if (name == null)   return typeRef;
  String packageName=typeRef.getPackageName();
  if (packageName == null)   return typeRef;
  String[] nameParts=name.split("\\$");
  String newName="";
  String sep="";
  for (int i=0; i < nameParts.length - 1; i++) {
    newName=newName + sep + nameParts[i];
    sep="$";
    String newInternalName=packageName.replaceAll("\\.","/") + "/" + newName;
    TypeReference newTypeRef=metadataSystem.lookupType(newInternalName);
    if (newTypeRef != null) {
      TypeDefinition newTypeDef=newTypeRef.resolve();
      if (newTypeDef != null) {
        return newTypeRef;
      }
    }
  }
  return typeRef;
}
