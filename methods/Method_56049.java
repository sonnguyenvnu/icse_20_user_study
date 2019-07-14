/** 
 * Sets the specified value to the  {@code waitValue.value64} field. 
 */
public CUstreamBatchMemOpParams waitValue_value64(@NativeType("cuuint64_t") long value){
  nwaitValue_value64(address(),value);
  return this;
}
