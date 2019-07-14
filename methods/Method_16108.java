@Override public InSpringDynamicDataSourceConfig add(InSpringDynamicDataSourceConfig config){
  return configMap.put(config.getId(),config);
}
