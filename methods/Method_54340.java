/** 
 * Returns the value of the  {@code cpuTimerFreq} field. 
 */
@NativeType("int64_t") public long cpuTimerFreq(){
  return ncpuTimerFreq(address());
}
