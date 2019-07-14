/** 
 * Close the stream. This method does not release the buffer, since its contents might still be required. Note: Invoking this method in this class will have no effect.
 */
public void close(){
  if (writer != null && count > 0) {
    flush();
  }
  if (buf.length <= BUFFER_THRESHOLD) {
    bufLocal.set(buf);
  }
  this.buf=null;
}
