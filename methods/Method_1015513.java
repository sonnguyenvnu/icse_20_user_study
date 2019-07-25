public Object down(Message msg){
  if (msg.isFlagSet(Message.Flag.NO_FC))   return down_prot.down(msg);
  Address dest=msg.getDest();
  boolean multicast=dest == null;
  boolean handle_multicasts=handleMulticastMessage();
  boolean process=(handle_multicasts && multicast) || (!handle_multicasts && !multicast);
  if (!process)   return down_prot.down(msg);
  int length=msg.getLength();
  if (length == 0)   return down_prot.down(msg);
  Object retval=handleDownMessage(msg);
  if (msg.isTransientFlagSet(Message.TransientFlag.DONT_LOOPBACK)) {
    long new_credits=adjustCredit(received,local_addr,length);
    if (new_credits > 0)     sendCredit(local_addr,new_credits);
  }
  return retval;
}
