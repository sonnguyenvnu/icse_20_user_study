/** 
 * Unsafe version of:  {@link #aiAttachLogStream AttachLogStream} 
 */
public static void naiAttachLogStream(long stream){
  long __functionAddress=Functions.AttachLogStream;
  if (CHECKS) {
    AILogStream.validate(stream);
  }
  invokePV(stream,__functionAddress);
}
