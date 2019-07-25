protected void set(String payload){
  logger.debug("Sending payload: {}",payload);
  coapClient.asyncPut(payload,this,scheduler);
}
