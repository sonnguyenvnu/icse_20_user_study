/** 
 * Returns the value of the  {@code cpuTimeBegin} field. 
 */
@NativeType("int64_t") public long cpuTimeBegin(){
  return ncpuTimeBegin(address());
}
