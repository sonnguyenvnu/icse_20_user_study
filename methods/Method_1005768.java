/** 
 * @param dependencies External Dependencies whose http file rule needs to be created
 * @return List of rules
 */
public static List<Rule> compose(Collection<ExternalDependency> dependencies){
  return dependencies.stream().sorted(ExternalDependency.compareByName).map(dependency -> {
    Rule rule=new HttpFile().mavenCoords(dependency.getMavenCoords()).sha256(DependencyUtils.shaSum256(dependency.getRealDependencyFile())).name(dependency.getTargetName());
    rule.name(dependency.getTargetName());
    return rule;
  }
).collect(Collectors.toList());
}
