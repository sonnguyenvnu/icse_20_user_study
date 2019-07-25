@Nullable private static Pair<PhpClass,Set<String>> invoke(@NotNull Project project,@NotNull YAMLKeyValue serviceKeyValue){
  String aClass=YamlHelper.getYamlKeyValueAsString(serviceKeyValue,"class");
  if (aClass == null || StringUtils.isBlank(aClass)) {
    return null;
  }
  PhpClass resolvedClassDefinition=ServiceUtil.getResolvedClassDefinition(project,aClass,new ContainerCollectionResolver.LazyServiceCollector(project));
  if (resolvedClassDefinition == null) {
    return null;
  }
  Set<String> phpClassServiceTags=ServiceUtil.getPhpClassServiceTags(resolvedClassDefinition);
  Set<String> strings=YamlHelper.collectServiceTags(serviceKeyValue);
  if (strings != null && strings.size() > 0) {
    for (    String s : strings) {
      if (phpClassServiceTags.contains(s)) {
        phpClassServiceTags.remove(s);
      }
    }
  }
  return Pair.create(resolvedClassDefinition,phpClassServiceTags);
}
