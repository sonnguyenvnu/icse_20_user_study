public KafkaConsumer<byte[],byte[]> register(Broker host,int partition,String topic){
  if (!_connections.containsKey(host)) {
    _connections.put(host,new ConnectionInfo(new KafkaConsumer<byte[],byte[]>(_config.getProperties()),topic,partition));
  }
  ConnectionInfo info=_connections.get(host);
  return info.consumer;
}
