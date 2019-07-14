/** 
 * Enables buffering by transferring the output to the buffer.
 */
protected void enableBuffering(){
  if (buffer != null) {
    return;
  }
  buffer=new Buffer();
}
