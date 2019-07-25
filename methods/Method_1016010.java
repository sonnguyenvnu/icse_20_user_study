@Override public QueueFactory<byte[]> clear(Services service,GridQueue queueName) throws IOException {
  QueueFactory<byte[]> factory=getConnector(service);
  factory.getQueue(queueName.name()).clear();
  return factory;
}
