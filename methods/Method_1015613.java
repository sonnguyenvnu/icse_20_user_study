public Object down(Message msg){
  Address dst=msg.getDest();
  if (dst == null || msg.isFlagSet(Message.Flag.NO_RELIABILITY))   return down_prot.down(msg);
  if (!running) {
    log.trace("%s: discarded message as start() has not yet been called, message: %s",local_addr,msg);
    return null;
  }
  if (msg.src() == null)   msg.src(local_addr);
  SenderEntry entry=getSenderEntry(dst);
  boolean dont_loopback_set=msg.isTransientFlagSet(Message.TransientFlag.DONT_LOOPBACK) && dst.equals(local_addr);
  short send_conn_id=entry.connId();
  long seqno=entry.sent_msgs_seqno.getAndIncrement();
  long sleep=10;
  do {
    try {
      msg.putHeader(this.id,UnicastHeader3.createDataHeader(seqno,send_conn_id,seqno == DEFAULT_FIRST_SEQNO));
      entry.msgs.add(seqno,msg,dont_loopback_set ? dont_loopback_filter : null);
      if (conn_expiry_timeout > 0)       entry.update();
      if (dont_loopback_set)       entry.msgs.purge(entry.msgs.getHighestDeliverable());
      break;
    }
 catch (    Throwable t) {
      if (running) {
        Util.sleep(sleep);
        sleep=Math.min(5000,sleep * 2);
      }
    }
  }
 while (running);
  if (is_trace) {
    StringBuilder sb=new StringBuilder();
    sb.append(local_addr).append(" --> ").append(dst).append(": DATA(").append("#").append(seqno).append(", conn_id=").append(send_conn_id);
    if (seqno == DEFAULT_FIRST_SEQNO)     sb.append(", first");
    sb.append(')');
    log.trace(sb);
  }
  num_msgs_sent++;
  return down_prot.down(msg);
}
