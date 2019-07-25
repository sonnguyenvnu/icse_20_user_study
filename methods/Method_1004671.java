private Supplier<ResolvedProjectDescription> resolve(ProjectDescription description,ProjectGenerationContext context){
  return () -> {
    context.getBeanProvider(ProjectDescriptionCustomizer.class).orderedStream().forEach((customizer) -> customizer.customize(description));
    return new ResolvedProjectDescription(description);
  }
;
}
