/** 
 * @param dependencies External Dependencies whose rule needs to be created
 * @return List of rules
 */
@SuppressWarnings("NullAway") public static List<Rule> compose(Collection<ExternalDependency> dependencies){
  return dependencies.stream().peek(dependency -> {
    if (!ImmutableSet.of(JAR,AAR).contains(dependency.getPackaging())) {
      throw new IllegalStateException("Dependency not a valid prebuilt: " + dependency);
    }
  }
).sorted(ExternalDependency.compareByName).map(dependency -> {
    Prebuilt rule=new Prebuilt().mavenCoords(dependency.getMavenCoords()).enableJetifier(dependency.enableJetifier()).sha256(DependencyUtils.shaSum256(dependency.getRealDependencyFile()));
    dependency.getRealSourceFile().ifPresent(file -> rule.sourcesSha256(DependencyUtils.shaSum256(file)));
    rule.ruleType(RuleType.PREBUILT.getBuckName()).deps(external(dependency.getDeps())).name(dependency.getTargetName());
    return rule;
  }
).collect(Collectors.toList());
}
