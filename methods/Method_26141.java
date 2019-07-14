private static Predicate<String> areAllReturnStatementsAssignable(ImmutableSet<ClassType> returnStatementsTypes){
  return s -> returnStatementsTypes.stream().map(MutableMethodReturnType::getImmutableSuperTypesForClassType).allMatch(c -> c.contains(s));
}
