/** 
 * Send all fragments as separate messages (with same ID !). Example: <pre> Given the generated ID is 2344, number of fragments=3, message {dst,src,buf} would be fragmented into: [2344,3,0]{dst,src,buf1}, [2344,3,1]{dst,src,buf2} and [2344,3,2]{dst,src,buf3} </pre>
 */
protected void fragment(Message msg){
  try {
    byte[] buffer=msg.getRawBuffer();
    int original_length=msg.getLength();
    int num_frags=(int)Math.ceil(original_length / (double)frag_size);
    num_frags_sent.add(num_frags);
    if (log.isTraceEnabled()) {
      Address dest=msg.getDest();
      log.trace("%s: fragmenting message to %s (size=%d) into %d fragment(s) [frag_size=%d]",local_addr,dest != null ? dest : "<all>",original_length,num_frags,frag_size);
    }
    int frag_id=getNextId();
    int total_size=original_length + msg.getOffset();
    int offset=msg.getOffset();
    int tmp_size=0, i=0;
    while (offset < total_size) {
      if (offset + frag_size <= total_size)       tmp_size=frag_size;
 else       tmp_size=total_size - offset;
      Frag3Header hdr=new Frag3Header(frag_id,i,num_frags,original_length,offset - msg.getOffset());
      Message frag_msg=msg.copy(false,i == 0).setBuffer(buffer,offset,tmp_size).putHeader(this.id,hdr);
      down_prot.down(frag_msg);
      offset+=tmp_size;
      i++;
    }
  }
 catch (  Exception e) {
    log.error(String.format("%s: fragmentation failure",local_addr),e);
  }
}
