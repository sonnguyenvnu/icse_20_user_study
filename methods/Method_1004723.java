static void init(Settings settings,Log log){
  InitializationUtils.checkIdForOperation(settings);
  InitializationUtils.setFieldExtractorIfNotSet(settings,HiveFieldExtractor.class,log);
  InitializationUtils.discoverClusterInfo(settings,log);
}
