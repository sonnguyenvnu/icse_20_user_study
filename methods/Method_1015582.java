public Object down(Message msg){
  Address target=msg.getDest();
  if ((target != null && !handle_unicasts) || !(msg.isFlagSet(Message.Flag.RSVP) || msg.isFlagSet(Message.Flag.RSVP_NB)))   return down_prot.down(msg);
  short next_id=getNextId();
  RsvpHeader hdr=new RsvpHeader(RsvpHeader.REQ,next_id);
  msg.putHeader(id,hdr);
  boolean block=msg.isFlagSet(Message.Flag.RSVP);
  Entry entry=target != null ? new Entry(target) : new Entry(members);
  Object retval=null;
  try {
    ids.put(next_id,entry);
    entry.retainAll(members);
    if (log.isTraceEnabled())     log.trace(local_addr + ": " + hdr.typeToString() + " --> " + (target == null ? "cluster" : target));
    retval=down_prot.down(msg);
    if (msg.isTransientFlagSet(Message.TransientFlag.DONT_LOOPBACK))     entry.ack(local_addr);
    if (block)     entry.block(timeout);
  }
 catch (  TimeoutException e) {
    if (throw_exception_on_timeout)     throw new RuntimeException(e);
 else     if (log.isWarnEnabled())     log.warn(Util.getMessage("RSVP_Timeout"),entry);
  }
 finally {
    if (block) {
      Entry tmp=ids.remove(next_id);
      if (tmp != null)       tmp.destroy();
    }
  }
  return retval;
}
