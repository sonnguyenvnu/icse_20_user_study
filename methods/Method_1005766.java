public static Set<String> targets(Set<Target> deps){
  return deps.stream().map(BuckRuleComposer::targets).collect(Collectors.toSet());
}
