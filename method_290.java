/** 
 * Handle upgrade to newer version
 */
private void _XXXXX_() throws RegistryException, IndeterminateConfigurationException {
  List<String> dbConsumers=Arrays.asList("update-db-artifact","update-db-repository-metadata");
  List<String> intersec=ListUtils.intersection(dbConsumers,configuration.getRepositoryScanning().getKnownContentConsumers());
  if (!intersec.isEmpty()) {
    List<String> knowContentConsumers=new ArrayList<>(configuration.getRepositoryScanning().getKnownContentConsumers().size());
    for (    String knowContentConsumer : configuration.getRepositoryScanning().getKnownContentConsumers()) {
      if (!dbConsumers.contains(knowContentConsumer)) {
        knowContentConsumers.add(knowContentConsumer);
      }
    }
    configuration.getRepositoryScanning().setKnownContentConsumers(knowContentConsumers);
  }
  if (!configuration.getRepositoryScanning().getKnownContentConsumers().contains("create-archiva-metadata")) {
    List<String> knowContentConsumers=new ArrayList<>(configuration.getRepositoryScanning().getKnownContentConsumers());
    knowContentConsumers.add("create-archiva-metadata");
    configuration.getRepositoryScanning().setKnownContentConsumers(knowContentConsumers);
  }
  if (!configuration.getRepositoryScanning().getKnownContentConsumers().contains("duplicate-artifacts")) {
    List<String> knowContentConsumers=new ArrayList<>(configuration.getRepositoryScanning().getKnownContentConsumers());
    knowContentConsumers.add("duplicate-artifacts");
    configuration.getRepositoryScanning().setKnownContentConsumers(knowContentConsumers);
  }
  Registry defaultOnlyConfiguration=readDefaultOnlyConfiguration();
  if (hasConfigVersionChanged(configuration,defaultOnlyConfiguration)) {
    updateCheckPathDefaults(configuration,defaultOnlyConfiguration);
    String newVersion=defaultOnlyConfiguration.getString("version");
    if (newVersion == null) {
      throw new IndeterminateConfigurationException("The default configuration has no version information!");
    }
    configuration.setVersion(newVersion);
    try {
      save(configuration);
    }
 catch (    IndeterminateConfigurationException e) {
      log.error("Error occured during configuration update to new version: {}",e.getMessage());
    }
catch (    RegistryException e) {
      log.error("Error occured during configuration update to new version: {}",e.getMessage());
    }
  }
}