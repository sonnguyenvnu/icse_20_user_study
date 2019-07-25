/** 
 * Flush the  {@code flushBuffer} to the breaker, incrementing the totalbytes and resetting the buffer.
 */
public void flush(){
  breaker.addEstimateBytesAndMaybeBreak(this.flushBuffer,this.fieldName);
  this.totalBytes+=this.flushBuffer;
  this.flushBuffer=0;
}
