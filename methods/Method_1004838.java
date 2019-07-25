@Override public void push(RowMap r) throws Exception {
  if (!r.shouldOutput(outputConfig)) {
    context.setPosition(r.getNextPosition());
    return;
  }
  String value=r.toJSON(outputConfig);
  String routingKey=getRoutingKeyFromTemplate(r);
  channel.basicPublish(exchangeName,routingKey,props,value.getBytes());
  if (r.isTXCommit()) {
    context.setPosition(r.getNextPosition());
  }
  if (LOGGER.isDebugEnabled()) {
    LOGGER.debug("->  routing key:" + routingKey + ", partition:" + value);
  }
}
