public ResultGeekQ<String> recharge(){
  ResultGeekQ<String> result=ResultGeekQ.build();
  long orderId=SnowflakeIdWorker.getOrderId(1,1);
  return result;
}
