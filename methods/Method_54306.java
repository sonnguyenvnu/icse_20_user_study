/** 
 * Unsafe version of  {@link #profiler_begin(BGFXProfilerBeginI) profiler_begin}. 
 */
public static void nprofiler_begin(long struct,BGFXProfilerBeginI value){
  memPutAddress(struct + BGFXCallbackVtbl.PROFILER_BEGIN,value.address());
}
