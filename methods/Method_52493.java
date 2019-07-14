private List<MethodType> searchImportedStaticMethods(String methodName,List<JavaTypeDefinition> typeArguments,int argArity,Class<?> accessingClass){
  List<MethodType> foundMethods=new ArrayList<>();
  List<JavaTypeDefinition> explicitImports=staticNamesToClasses.get(methodName);
  if (explicitImports != null) {
    for (    JavaTypeDefinition anImport : explicitImports) {
      foundMethods.addAll(getApplicableMethods(anImport,methodName,typeArguments,argArity,accessingClass));
    }
  }
  if (!foundMethods.isEmpty()) {
    return foundMethods;
  }
  for (  JavaTypeDefinition anOnDemandImport : importOnDemandStaticClasses) {
    foundMethods.addAll(getApplicableMethods(anOnDemandImport,methodName,typeArguments,argArity,accessingClass));
  }
  return foundMethods;
}
