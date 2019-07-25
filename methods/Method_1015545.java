protected void deliver(Message msg,Address sender,long seqno,String error_msg){
  if (is_trace)   log.trace("%s <-- %s: #%d",local_addr,sender,seqno);
  try {
    up_prot.up(msg);
  }
 catch (  Throwable t) {
    log.error(Util.getMessage("FailedToDeliverMsg"),local_addr,error_msg,msg,t);
  }
}
