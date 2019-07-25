@Nullable private static Collection<ServiceTagInterface> create(@NotNull YAMLKeyValue yamlHash){
  final Collection<ServiceTagInterface> tags=new ArrayList<>();
  YamlHelper.visitTagsOnServiceDefinition(yamlHash,args -> {
    String methodName=args.getAttribute("method");
    if (StringUtils.isBlank(methodName)) {
      return;
    }
    tags.add(args);
  }
);
  return tags;
}
