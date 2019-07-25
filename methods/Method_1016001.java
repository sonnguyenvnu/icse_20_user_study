@Override public QueueFactory<byte[]> send(Services serviceName,GridQueue queueName,byte[] message) throws IOException {
  if (this.rabbitQueueFactory == null && this.rabbitMQ_host != null) {
    connectRabbitMQ(this.rabbitMQ_host,this.rabbitMQ_port,this.rabbitMQ_username,this.rabbitMQ_password);
  }
  if (this.rabbitQueueFactory == null) {
    this.rabbitMQ_host=null;
  }
 else   try {
    this.rabbitQueueFactory.getQueue(serviceQueueName(serviceName,queueName)).send(message);
    Data.logger.info("Broker/Client: send rabbitMQ service '" + serviceName + "', queue '" + queueName + "', message:" + messagePP(message));
    return this.rabbitQueueFactory;
  }
 catch (  IOException e) {
    String m=e.getMessage();
    if (m == null)     m=e.getCause().getMessage();
    if (m.equals(TARGET_LIMIT_MESSAGE))     throw e;
    Data.logger.debug("Broker/Client: send rabbitMQ service '" + serviceName + "', queue '" + queueName + "', rabbitmq fail",e);
  }
  if (this.mcpQueueFactory == null && this.mcp_host != null) {
    connectMCP(this.mcp_host,this.mcp_port);
    if (this.mcpQueueFactory == null) {
      Data.logger.warn("Broker/Client: FATAL: connection to MCP lost! send mcp service '" + serviceName + "', queue '" + queueName);
    }
  }
  if (this.mcpQueueFactory != null)   try {
    this.mcpQueueFactory.getQueue(serviceQueueName(serviceName,queueName)).send(message);
    Data.logger.info("Broker/Client: send mcp service '" + serviceName + "', queue '" + queueName + "', message:" + messagePP(message));
    return this.mcpQueueFactory;
  }
 catch (  IOException e) {
    Data.logger.debug("Broker/Client: send mcp service '" + serviceName + "', queue '" + queueName + "',mcp fail",e);
  }
  Data.logger.info("Broker/Client: send() on peer broker/local db");
  return super.send(serviceName,queueName,message);
}
