@Override public MessageContainer<byte[]> receive(Services serviceName,GridQueue queueName,long timeout,boolean autoAck) throws IOException {
  if (this.rabbitQueueFactory == null && this.rabbitMQ_host != null) {
    connectRabbitMQ(this.rabbitMQ_host,this.rabbitMQ_port,this.rabbitMQ_username,this.rabbitMQ_password);
  }
  if (this.rabbitQueueFactory == null) {
    this.rabbitMQ_host=null;
  }
 else   try {
    Queue<byte[]> rabbitQueue=this.rabbitQueueFactory.getQueue(serviceQueueName(serviceName,queueName));
    MessageContainer<byte[]> mc=rabbitQueue.receive(timeout,autoAck);
    if (mc != null && mc.getPayload() != null && mc.getPayload().length > 0)     Data.logger.info("Broker/Client: received rabbitMQ service '" + serviceName + "', queue '" + queueName + "', message:" + messagePP(mc.getPayload()));
    return mc;
  }
 catch (  IOException e) {
    Data.logger.debug("Broker/Client: receive rabbitMQ service '" + serviceName + "', queue '" + queueName + "',rabbitmq fail",e);
  }
  if (this.mcpQueueFactory == null && this.mcp_host != null) {
    connectMCP(this.mcp_host,this.mcp_port);
    if (this.mcpQueueFactory == null) {
      Data.logger.warn("Broker/Client: FATAL: connection to MCP lost! receive mcp service '" + serviceName + "', queue '" + queueName);
    }
  }
  if (this.mcpQueueFactory != null)   try {
    Queue<byte[]> mcpQueue=this.mcpQueueFactory.getQueue(serviceQueueName(serviceName,queueName));
    MessageContainer<byte[]> mc=mcpQueue.receive(timeout,autoAck);
    if (mc != null && mc.getPayload() != null && mc.getPayload().length > 0)     Data.logger.info("Broker/Client: receive mcp service '" + serviceName + "', queue '" + queueName + "', message:" + messagePP(mc.getPayload()));
    return mc;
  }
 catch (  IOException e) {
    Data.logger.debug("Broker/Client: receive mcp service '" + serviceName + "', queue '" + queueName + "',mcp fail",e);
  }
  Data.logger.info("Broker/Client: receive() on peer broker/local db");
  MessageContainer<byte[]> mc=super.receive(serviceName,queueName,timeout,autoAck);
  if (mc != null && mc.getPayload() != null && mc.getPayload().length > 0)   Data.logger.info("Broker/Client: received peer broker/local db service '" + serviceName + "', queue '" + queueName + "', message:" + messagePP(mc.getPayload()));
  return mc;
}
