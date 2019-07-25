protected void deliver(Address dest,Address sender,final Message msg){
  try {
    Message copy=copy(msg).dest(dest).src(sender);
    if (log.isTraceEnabled())     log.trace(local_addr + ": delivering message from " + sender);
    long start=stats ? System.nanoTime() : 0;
    up_prot.up(copy);
    if (stats) {
      local_delivery_time.add(System.nanoTime() - start);
      local_deliveries.increment();
    }
  }
 catch (  Exception e) {
    log.error(Util.getMessage("FailedDeliveringMessage"),e);
  }
}
