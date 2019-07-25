/** 
 * Called by the sender to resend messages for which no ACK has been received yet 
 */
protected void retransmit(Message msg){
  if (is_trace) {
    UnicastHeader3 hdr=msg.getHeader(id);
    long seqno=hdr != null ? hdr.seqno : -1;
    log.trace("%s --> %s: resending(#%d)",local_addr,msg.getDest(),seqno);
  }
  down_prot.down(msg);
  num_xmits++;
}
