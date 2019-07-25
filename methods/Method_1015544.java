/** 
 * Adds the message to the sent_msgs table and then passes it down the stack. Change Bela Ban May 26 2002: we don't store a copy of the message, but a reference ! This saves us a lot of memory. However, this also means that a message should not be changed after storing it in the sent-table ! See protocols/DESIGN for details. Made seqno increment and adding to sent_msgs atomic, e.g. seqno won't get incremented if adding to sent_msgs fails e.g. due to an OOM (see http://jira.jboss.com/jira/browse/JGRP-179). bela Jan 13 2006
 */
protected void send(Message msg){
  if (!running) {
    log.trace("%s: discarded message as we're not in the 'running' state, message: %s",local_addr,msg);
    return;
  }
  long msg_id;
  Table<Message> buf=xmit_table.get(local_addr);
  if (buf == null)   return;
  if (msg.src() == null)   msg.src(local_addr);
  boolean dont_loopback_set=msg.isTransientFlagSet(Message.TransientFlag.DONT_LOOPBACK);
  msg_id=seqno.incrementAndGet();
  long sleep=10;
  do {
    try {
      msg.putHeader(this.id,NakAckHeader2.createMessageHeader(msg_id));
      buf.add(msg_id,msg,dont_loopback_set ? dont_loopback_filter : null);
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
  if (is_trace)   log.trace("%s --> [all]: #%d",local_addr,msg_id);
  down_prot.down(msg);
  num_messages_sent++;
  if (resend_last_seqno && last_seqno_resender != null)   last_seqno_resender.skipNext();
}
