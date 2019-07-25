@Override public QueueFactory<byte[]> reject(Services serviceName,GridQueue queueName,long deliveryTag) throws IOException {
  if (this.rabbitQueueFactory == null && this.rabbitMQ_host != null) {
    connectRabbitMQ(this.rabbitMQ_host,this.rabbitMQ_port,this.rabbitMQ_username,this.rabbitMQ_password);
  }
  if (this.rabbitQueueFactory == null) {
    this.rabbitMQ_host=null;
  }
 else   try {
    this.rabbitQueueFactory.getQueue(serviceQueueName(serviceName,queueName)).reject(deliveryTag);
    Data.logger.info("Broker/Client: rejected rabbitMQ service '" + serviceName + "', queue '" + queueName + "', deliveryTag " + deliveryTag);
    return this.rabbitQueueFactory;
  }
 catch (  IOException e) {
    Data.logger.debug("Broker/Client: acknowledge rabbitMQ service '" + serviceName + "', queue '" + queueName + "', rabbitmq fail",e);
  }
  if (this.mcpQueueFactory == null && this.mcp_host != null) {
    connectMCP(this.mcp_host,this.mcp_port);
    if (this.mcpQueueFactory == null) {
      Data.logger.warn("Broker/Client: FATAL: connection to MCP lost! send mcp service '" + serviceName + "', queue '" + queueName);
    }
  }
  if (this.mcpQueueFactory != null)   try {
    this.mcpQueueFactory.getQueue(serviceQueueName(serviceName,queueName)).reject(deliveryTag);
    Data.logger.info("Broker/Client: rejected mcp service '" + serviceName + "', queue '" + queueName + "', deliveryTag " + deliveryTag);
    return this.mcpQueueFactory;
  }
 catch (  IOException e) {
    Data.logger.debug("Broker/Client: acknowledge mcp service '" + serviceName + "', queue '" + queueName + "',mcp fail",e);
  }
  Data.logger.info("Broker/Client: reject() on peer broker/local db");
  return super.reject(serviceName,queueName,deliveryTag);
}
