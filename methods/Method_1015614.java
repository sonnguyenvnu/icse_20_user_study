/** 
 * Sends a retransmit request to the given sender 
 */
protected void retransmit(SeqnoList missing,Address sender){
  Message xmit_msg=new Message(sender).setBuffer(Util.streamableToBuffer(missing)).setFlag(Message.Flag.OOB,Message.Flag.INTERNAL).putHeader(id,UnicastHeader3.createXmitReqHeader());
  if (is_trace)   log.trace("%s --> %s: XMIT_REQ(%s)",local_addr,sender,missing);
  down_prot.down(xmit_msg);
  xmit_reqs_sent.add(missing.size());
}
