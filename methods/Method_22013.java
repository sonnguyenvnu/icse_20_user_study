private Optional<LiteJobConfiguration> find(){
  if (!jobNodeStorage.isJobNodeExisted(ConfigurationNode.ROOT)) {
    return Optional.absent();
  }
  LiteJobConfiguration result=LiteJobConfigurationGsonFactory.fromJson(jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.ROOT));
  if (null == result) {
    jobNodeStorage.removeJobNodeIfExisted(ConfigurationNode.ROOT);
  }
  return Optional.fromNullable(result);
}
