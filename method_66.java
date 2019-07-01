@Override public void _XXXXX_(Config config,MetricRegistry registry){
  LOG.debug("Preparing kafka-sink");
  KafkaReporter.Builder builder=KafkaReporter.forRegistry(registry).topic(config.getString("topic")).config(config);
  if (config.hasPath(MetricConfigs.TAGS_FIELD_NAME)) {
    builder.addFields(config.getConfig(MetricConfigs.TAGS_FIELD_NAME).root().unwrapped());
  }
  reporter=builder.build();
  LOG.info("Prepared kafka-sink");
}