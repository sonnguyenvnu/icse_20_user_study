@Override public void apply(@NotNull Collection<TemplateMappingConfiguration> tmc){
  for (  TemplateMappingConfiguration templateMappingConfiguration : tmc) {
    TemplateModule module=templateMappingConfiguration.getModel().getModule();
    SModuleReference moduleReference=module.getModuleReference();
    myGenerators.add(moduleReference);
  }
}
