/** 
 * Enable verbose logging. Verbose logging includes debug-related stuff and detailed import statistics. This can have severe impact on import performance and memory consumption. However, it might be useful to find out why a file didn't read correctly.
 * @param d true or false, your decision
 */
public static void aiEnableVerboseLogging(@NativeType("aiBool") boolean d){
  long __functionAddress=Functions.EnableVerboseLogging;
  invokeV(d ? 1 : 0,__functionAddress);
}
