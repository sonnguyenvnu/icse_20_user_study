public Type erasure(){
  return new Type(typeUtils,elementUtils,typeFactory,accessorNaming,typeUtils.erasure(typeMirror),typeElement,typeParameters,implementationType,componentType,packageName,name,qualifiedName,isInterface,isEnumType,isIterableType,isCollectionType,isMapType,isStream,toBeImportedTypes,notToBeImportedTypes,isToBeImported,isLiteral);
}
