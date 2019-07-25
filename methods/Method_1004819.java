private void init(String location,Job job,boolean read){
  Settings settings=HadoopSettingsManager.loadFrom(job.getConfiguration()).merge(properties);
  settings=(read ? settings.setResourceRead(location) : settings.setResourceWrite(location));
  InitializationUtils.checkIdForOperation(settings);
  InitializationUtils.setValueWriterIfNotSet(settings,PigValueWriter.class,log);
  InitializationUtils.setValueReaderIfNotSet(settings,PigValueReader.class,log);
  InitializationUtils.setBytesConverterIfNeeded(settings,PigBytesConverter.class,log);
  InitializationUtils.setFieldExtractorIfNotSet(settings,PigFieldExtractor.class,log);
  InitializationUtils.setUserProviderIfNotSet(settings,HadoopUserProvider.class,log);
  InitializationUtils.discoverClusterInfo(settings,log);
  isJSON=settings.getOutputAsJson();
}
