/** 
 * 1. Get all the fragment buffers 2. When all are received -> Assemble them into one big buffer 3. Read headers and byte buffer from big buffer 4. Set headers and buffer in msg 5. Return the message
 */
protected Message unfragment(Message msg,Frag3Header hdr){
  Address sender=msg.getSrc();
  Message assembled_msg=null;
  ConcurrentMap<Integer,FragEntry> frag_table=fragment_list.get(sender);
  if (frag_table == null) {
    frag_table=Util.createConcurrentMap(16,.075f,16);
    ConcurrentMap<Integer,FragEntry> tmp=fragment_list.putIfAbsent(sender,frag_table);
    if (tmp != null)     frag_table=tmp;
  }
  num_frags_received.increment();
  FragEntry entry=frag_table.get(hdr.id);
  if (entry == null) {
    entry=new FragEntry(hdr.num_frags);
    FragEntry tmp=frag_table.putIfAbsent(hdr.id,entry);
    if (tmp != null)     entry=tmp;
  }
  if ((assembled_msg=entry.set(msg,hdr)) != null) {
    frag_table.remove(hdr.id);
    if (log.isTraceEnabled())     log.trace("%s: unfragmented message from %s (size=%d) from %d fragments",local_addr,sender,assembled_msg.getLength(),entry.num_frags);
  }
  return assembled_msg;
}
