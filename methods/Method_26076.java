private static boolean referencesExternalMethods(MethodDetails methodDetails,Set<MethodSymbol> localMethods){
  return !Sets.difference(methodDetails.methodsReferenced,localMethods).isEmpty();
}
