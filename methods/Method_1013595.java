/** 
 * Skips over the next <code>n</code> bytes in this stream, or throws <code>EOFException</code> if it contains fewer than <code>n</code> bytes. 
 */
public final void skip(int n) throws IOException, EOFException {
  while (this.len > 0 && this.curr + n >= this.len) {
    n-=(this.len - this.curr);
    this.len=this.in.read(this.buff);
    Assert.check(this.len != 0,EC.SYSTEM_STREAM_EMPTY);
    this.curr=0;
  }
  if (n > 0 && this.len < 0)   throw new EOFException();
  this.curr+=n;
  Assert.check(this.len < 0 || this.curr < this.len,EC.SYSTEM_INDEX_ERROR);
}
