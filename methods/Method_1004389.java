@Override protected void ack(BaseMessage message,long elapsed,Throwable exception,Map<String,String> attachment){
  PulledMessage pulledMessage=(PulledMessage)message;
  if (pulledMessage.hasNotAcked()) {
    AckHelper.ackWithTrace(pulledMessage,exception);
  }
}
