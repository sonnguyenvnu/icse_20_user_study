@Override public void onApplicationEvent(ApplicationStartedEvent event){
  if (!isCalled.compareAndSet(false,true)) {
    return;
  }
  System.setProperty("sun.net.client.defaultConnectTimeout",msConfigProperties.getConnectTimeout());
  System.setProperty("sun.net.client.defaultReadTimeout",msConfigProperties.getReadTimeout());
  try {
    DefaultProfile.addEndpoint("cn-hangzhou","cn-hangzhou",SmsProperties.SMS_PRODUCT,SmsProperties.SMS_DOMAIN);
    Collection<MessageListener> messageListeners=event.getApplicationContext().getBeansOfType(MessageListener.class).values();
    if (messageListeners.isEmpty()) {
      return;
    }
    for (    MessageListener messageListener : messageListeners) {
      if (SmsReportMessageListener.class.isInstance(messageListener)) {
        if (msConfigProperties.getReportQueueName() != null && msConfigProperties.getReportQueueName().trim().length() > 0) {
          smsService.startSmsReportMessageListener((SmsReportMessageListener)messageListener);
          continue;
        }
        throw new IllegalArgumentException("the SmsReport queue name for " + messageListener.getClass().getCanonicalName() + " must be set.");
      }
      if (SmsUpMessageListener.class.isInstance(messageListener)) {
        if (msConfigProperties.getUpQueueName() != null && msConfigProperties.getUpQueueName().trim().length() > 0) {
          smsService.startSmsUpMessageListener((SmsUpMessageListener)messageListener);
          continue;
        }
        throw new IllegalArgumentException("the SmsUp queue name for " + messageListener.getClass().getCanonicalName() + " must be set.");
      }
    }
  }
 catch (  ClientException e) {
    throw new RuntimeException("initialize sms profile end point cause an exception");
  }
}
