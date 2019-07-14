@Override protected void onInit(){
  if (consumerProperties == null || !consumerProperties.getExtension().getEnabled()) {
    return;
  }
  super.onInit();
  if (this.retryTemplate != null) {
    Assert.state(getErrorChannel() == null,"Cannot have an 'errorChannel' property when a 'RetryTemplate' is " + "provided; use an 'ErrorMessageSendingRecoverer' in the 'recoveryCallback' property to " + "send an error message when retries are exhausted");
  }
  BindingRocketMQListener listener=new BindingRocketMQListener();
  rocketMQListenerContainer.setRocketMQListener(listener);
  if (retryTemplate != null) {
    this.retryTemplate.registerListener(listener);
  }
  try {
    rocketMQListenerContainer.afterPropertiesSet();
  }
 catch (  Exception e) {
    log.error("rocketMQListenerContainer init error: " + e.getMessage(),e);
    throw new IllegalArgumentException("rocketMQListenerContainer init error: " + e.getMessage(),e);
  }
  instrumentationManager.addHealthInstrumentation(new Instrumentation(rocketMQListenerContainer.getTopic() + rocketMQListenerContainer.getConsumerGroup()));
}
