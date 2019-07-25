protected void retransmit(SeqnoList missing_msgs,final Address sender,boolean multicast_xmit_request){
  Address dest=(multicast_xmit_request || this.use_mcast_xmit_req) ? null : sender;
  if (xmit_from_random_member && !local_addr.equals(sender)) {
    Address random_member=Util.pickRandomElement(members);
    if (random_member != null && !local_addr.equals(random_member))     dest=random_member;
  }
  Message retransmit_msg=new Message(dest).setBuffer(Util.streamableToBuffer(missing_msgs)).setFlag(Message.Flag.OOB,Message.Flag.INTERNAL).putHeader(this.id,NakAckHeader2.createXmitRequestHeader(sender));
  log.trace("%s --> %s: XMIT_REQ(%s)",local_addr,dest,missing_msgs);
  down_prot.down(retransmit_msg);
  if (stats)   xmit_reqs_sent.add(missing_msgs.size());
}
