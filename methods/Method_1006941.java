/** 
 * Send one message per item in the arguments list using the default destination of the jms template. If the recovery is successful  {@code null} is returned.
 * @see org.springframework.retry.interceptor.MethodInvocationRecoverer#recover(Object[],Throwable)
 */
@Override @Nullable public T recover(Object[] items,Throwable cause){
  try {
    for (    Object item : items) {
      jmsTemplate.convertAndSend(item);
    }
    return null;
  }
 catch (  JmsException e) {
    logger.error("Could not recover because of JmsException.",e);
    throw e;
  }
}
