private void checkTopic(String topic){
  try {
    Validators.checkTopic(topic);
  }
 catch (  MQClientException e) {
    throw new AssertionError(e);
  }
}
