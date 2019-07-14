/** 
 * Unsafe version of:  {@link #aiDetachLogStream DetachLogStream} 
 */
public static int naiDetachLogStream(long stream){
  long __functionAddress=Functions.DetachLogStream;
  if (CHECKS) {
    AILogStream.validate(stream);
  }
  return invokePI(stream,__functionAddress);
}
