static PartitionsCalculator load(final ObjectMapper objectMapper,final String instanceType,final NakadiSettings nakadiSettings) throws IOException {
  try (InputStream in=PartitionsCalculator.class.getResourceAsStream(PARTITION_STATISTICS)){
    if (null == in) {
      throw new IOException("Resource with name " + PARTITION_STATISTICS + " is not found");
    }
    return load(objectMapper,instanceType,in,nakadiSettings.getDefaultTopicPartitionCount(),nakadiSettings.getMaxTopicPartitionCount());
  }
 }
