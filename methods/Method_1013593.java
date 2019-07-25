/** 
 * Re-open this input stream on <code>is</code>. This method need not be called on a newly initialized stream, but it can be called after the stream has been closed to re-open the stream on a different underlying stream without requiring internal resources to be re-allocated. 
 */
public void open(InputStream is) throws IOException {
  Assert.check(this.in == null,EC.SYSTEM_STREAM_EMPTY);
  this.in=is;
  this.len=this.in.read(this.buff);
  Assert.check(this.len != 0,EC.SYSTEM_STREAM_EMPTY);
}
