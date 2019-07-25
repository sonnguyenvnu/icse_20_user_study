/** 
 * Uses the annotation processors scope list to generate the java_annotation_processor rules. It sorts them based on the the annotation processor's UID.
 * @param scopeList List of annotation processor scopes.
 * @return A list containing java_annotation_processor rules.
 */
public static List<Rule> compose(Collection<Scope> scopeList){
  return scopeList.stream().filter(scope -> !scope.getAnnotationProcessors().isEmpty()).sorted((scope1,scope2) -> scope1.getAnnotationProcessorPlugin().pluginUID().compareToIgnoreCase(scope2.getAnnotationProcessorPlugin().pluginUID())).map(scope -> {
    ImmutableSet.Builder<String> depsBuilder=new ImmutableSet.Builder<>();
    depsBuilder.addAll(externalApt(filterJar(scope.getExternalDeps())));
    depsBuilder.addAll(targetsApt(scope.getTargetDeps()));
    return new JavaAnnotationProcessorRule().processorClasses(scope.getAnnotationProcessors()).name(getApPluginRuleName(scope.getAnnotationProcessorPlugin())).deps(depsBuilder.build()).ruleType(RuleType.JAVA_ANNOTATION_PROCESSOR.getBuckName());
  }
).collect(Collectors.toList());
}
