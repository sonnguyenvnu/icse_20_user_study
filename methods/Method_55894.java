/** 
 * Unsafe version of:  {@link #cuProfilerInitialize ProfilerInitialize} 
 */
public static int ncuProfilerInitialize(long configFile,long outputFile,int outputMode){
  long __functionAddress=Functions.ProfilerInitialize;
  return callPPI(configFile,outputFile,outputMode,__functionAddress);
}
