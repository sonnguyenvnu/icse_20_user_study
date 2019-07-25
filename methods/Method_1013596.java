/** 
 * Reopen this output stream on <code>os</code>. This method need not be called on a newly initialized stream, but it can be called after the stream has been closed to re-open the stream on a different underlying stream without requiring internal resources to be re-allocated. 
 */
public void open(OutputStream os) throws IOException {
  this.out=os;
  this.len=0;
}
