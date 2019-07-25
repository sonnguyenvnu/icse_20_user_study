public void apply(MPSConfigurationBean data){
  data.setUseTransientOutputFolder(isUseTransientOutputFolder());
  data.setUseModuleSourceFolder(isUseModuleSourceFolder());
  data.setGeneratorOutputPath(getGeneratorOutputPath());
}
