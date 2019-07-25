@Override public List<GroupedConsumer> consumers(final String subject){
  final List<ClientMetaInfo> metaInfos=clientMetaInfoStore.queryConsumer(subject);
  final Map<String,GroupedConsumer> groupedConsumers=new HashMap<>();
  for (  ClientMetaInfo clientMetaInfo : metaInfos) {
    GroupedConsumer groupedConsumer=groupedConsumers.get(clientMetaInfo.getConsumerGroup());
    if (groupedConsumer == null) {
      groupedConsumer=new GroupedConsumer(NEW_QMQ_NAMESPACE,subject,clientMetaInfo.getConsumerGroup());
      groupedConsumers.put(clientMetaInfo.getConsumerGroup(),groupedConsumer);
    }
    List<String> endpoints=groupedConsumer.getEndPoint();
    if (endpoints == null) {
      endpoints=new ArrayList<>();
      groupedConsumer.setEndPoint(endpoints);
    }
    endpoints.add(buildNewQmqEndPoint(clientMetaInfo));
  }
  return new ArrayList<>(groupedConsumers.values());
}
