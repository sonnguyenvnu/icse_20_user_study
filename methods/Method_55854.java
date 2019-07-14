/** 
 * Unsafe version of:  {@link #cuThreadExchangeStreamCaptureMode ThreadExchangeStreamCaptureMode} 
 */
public static int ncuThreadExchangeStreamCaptureMode(long mode){
  long __functionAddress=Functions.ThreadExchangeStreamCaptureMode;
  return callPI(mode,__functionAddress);
}
