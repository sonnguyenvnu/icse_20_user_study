private static Optional<String> getCommonImmutableTypeForAllReturnStatementsTypes(ImmutableSet<ClassType> returnStatementsTypes){
  checkState(!returnStatementsTypes.isEmpty());
  ClassType arbitraryClassType=returnStatementsTypes.asList().get(0);
  ImmutableList<String> superTypes=getImmutableSuperTypesForClassType(arbitraryClassType);
  return superTypes.stream().filter(areAllReturnStatementsAssignable(returnStatementsTypes)).findFirst();
}
