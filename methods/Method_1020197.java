/** 
 * ????
 * @param order ??
 */
public void create(Order order){
  Date orderTime=new Date();
  this.orderMapper.insert(order);
  BrokerMessageLogPO messageLogPO=new BrokerMessageLogPO();
  messageLogPO.setMessageId(order.getMessageId());
  messageLogPO.setMessage(FastJsonConvertUtils.convertObjectToJson(order));
  messageLogPO.setTryCount(0);
  messageLogPO.setStatus(Constants.OrderSendStatus.SENDING);
  messageLogPO.setNextRetry(DateUtils.addMinutes(orderTime,Constants.ORDER_TIMEOUT));
  this.brokerMessageLogMapper.insert(messageLogPO);
  this.orderSender.send(order);
}
