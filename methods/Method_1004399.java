@Override public Connection route(Message message){
  ClientType clientType=DelayUtil.isDelayMessage(message) ? ClientType.DELAY_PRODUCER : ClientType.PRODUCER;
  String key=clientType.getCode() + "|" + message.getSubject();
  NettyConnection connection=cached.get(key);
  if (connection != null)   return connection;
  connection=new NettyConnection(message.getSubject(),clientType,producerClient,brokerService);
  NettyConnection old=cached.putIfAbsent(key,connection);
  if (old == null) {
    connection.init();
    return connection;
  }
  return old;
}
