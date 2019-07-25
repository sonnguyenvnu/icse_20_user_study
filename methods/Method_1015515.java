/** 
 * 1. Get all the fragment buffers 2. When all are received -> Assemble them into one big buffer 3. Read headers and byte buffer from big buffer 4. Set headers and buffer in msg 5. Pass msg up the stack
 */
private Message unfragment(Message msg,FragHeader hdr){
  Address sender=msg.getSrc();
  FragmentationTable frag_table=fragment_list.get(sender);
  if (frag_table == null) {
    frag_table=new FragmentationTable(sender);
    try {
      fragment_list.add(sender,frag_table);
    }
 catch (    IllegalArgumentException x) {
      frag_table=fragment_list.get(sender);
    }
  }
  num_received_frags++;
  byte[] buf=frag_table.add(hdr.id,hdr.frag_id,hdr.num_frags,msg.getBuffer());
  if (buf == null)   return null;
  try {
    DataInput in=new ByteArrayDataInputStream(buf);
    Message assembled_msg=new Message(false);
    assembled_msg.readFrom(in);
    assembled_msg.setSrc(sender);
    if (log.isTraceEnabled())     log.trace("assembled_msg is " + assembled_msg);
    num_received_msgs++;
    return assembled_msg;
  }
 catch (  Exception e) {
    log.error(Util.getMessage("FailedUnfragmentingAMessage"),e);
    return null;
  }
}
