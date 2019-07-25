@Override public QueueFactory<byte[]> acknowledge(Services service,GridQueue queueName,long deliveryTag) throws IOException {
  QueueFactory<byte[]> factory=getConnector(service);
  factory.getQueue(queueName.name()).acknowledge(deliveryTag);
  return factory;
}
