@VisibleForTesting protected static PartitionsCalculator load(final ObjectMapper objectMapper,final String instanceType,final InputStream in,final int defaultPartitionsCount,final int maxPartitionCount) throws IOException {
  final InstanceInfo[] instanceInfos=objectMapper.readValue(in,InstanceInfo[].class);
  final InstanceInfo instanceInfo=Stream.of(instanceInfos).filter(ii -> instanceType.equals(ii.getName())).findAny().orElseThrow(() -> new IllegalArgumentException("Failed to find instance " + instanceType + " configuration"));
  return new PartitionsCalculator(instanceInfo,defaultPartitionsCount,maxPartitionCount);
}
