@Override public void deliver(Message message){
  message.setDest(localAddress);
  if (log.isTraceEnabled()) {
    log.trace("Deliver message %s (%s) in total order",message,message.getHeader(id));
  }
  up_prot.up(message);
  statsCollector.incrementMessageDeliver();
}
