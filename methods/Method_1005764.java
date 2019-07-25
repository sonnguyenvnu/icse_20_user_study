public static Set<String> external(Set<ExternalDependency> deps){
  return deps.stream().map(BuckRuleComposer::external).collect(Collectors.toSet());
}
