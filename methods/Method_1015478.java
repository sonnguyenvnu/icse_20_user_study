/** 
 * Create a copy of the message. If offset and length are used (to refer to another buffer), the copy will contain only the subset offset and length point to, copying the subset into the new copy.<p/> Note that for headers, only the arrays holding references to the headers are copied, not the headers themselves ! The consequence is that the headers array of the copy hold the *same* references as the original, so do *not modify the headers ! If you want to change a header, copy it and call  {@link Message#putHeader(short,Header)} again.
 * @param copy_buffer
 * @param copy_headers Copy the headers
 * @return Message with specified data
 */
public Message copy(boolean copy_buffer,boolean copy_headers){
  Message retval=new Message(false);
  retval.dest=dest;
  retval.sender=sender;
  short tmp_flags=this.flags;
  byte tmp_tflags=this.transient_flags;
  retval.flags=tmp_flags;
  retval.transient_flags=tmp_tflags;
  if (copy_buffer && buf != null)   retval.setBuffer(buf,offset,length);
  retval.headers=copy_headers && headers != null ? Headers.copy(this.headers) : createHeaders(Util.DEFAULT_HEADERS);
  return retval;
}
