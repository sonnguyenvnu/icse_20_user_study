@Override public void init(){
  ExtensionPoint<ConfigurationType> configurationExtensionPoint=Extensions.getRootArea().getExtensionPoint(ConfigurationType.CONFIGURATION_TYPE_EP);
{
    ConfigTypeEnvoy runConfigurationKind=new ConfigTypeEnvoy("Build Script",AllIcons.RunConfigurations.Application,"Build Script","Build Script");
    runConfigurationKind.addFactoryFor("Build Script",BuildScript_Configuration.class);
    RunConfigurationsInitializer_AppPluginPart.this.myRegisteredKinds.add(runConfigurationKind);
    configurationExtensionPoint.registerExtension(runConfigurationKind);
  }
  ExtensionPoint<RuntimeConfigurationProducer> producerExtensionPoint=Extensions.getArea(null).getExtensionPoint(RuntimeConfigurationProducer.RUNTIME_CONFIGURATION_PRODUCER);
  for (  ConfigurationType ext : configurationExtensionPoint.getExtensions()) {
    if ("Build Script".equals(ext.getId())) {
      List<RuntimeConfigurationProducer> configurationProducers=BuildScript_Producer.getProducers(ext);
      ListSequence.fromList(RunConfigurationsInitializer_AppPluginPart.this.myRegisteredProducers).addSequence(ListSequence.fromList(configurationProducers));
      for (      RuntimeConfigurationProducer producer : ListSequence.fromList(configurationProducers)) {
        producerExtensionPoint.registerExtension(producer);
      }
      break;
    }
  }
}
