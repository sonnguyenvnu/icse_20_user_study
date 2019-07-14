/** 
 * Returns the process ID of the calling process. 
 */
@NativeType("pid_t") public static long getpid(){
  long __functionAddress=Functions.getpid;
  return invokeP(__functionAddress);
}
