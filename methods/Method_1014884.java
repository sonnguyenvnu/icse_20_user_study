public void produce(String topic,String message){
  try {
    kafkaTemplate.send(topic,message);
  }
 catch (  Exception e) {
    logger.info(e.getMessage());
  }
}
