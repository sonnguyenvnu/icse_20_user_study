/** 
 * Returns the value of the  {@code cpuTimeElapsed} field. 
 */
@NativeType("int64_t") public long cpuTimeElapsed(){
  return ncpuTimeElapsed(address());
}
