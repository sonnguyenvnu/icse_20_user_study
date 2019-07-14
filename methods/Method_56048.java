/** 
 * Sets the specified value to the  {@code waitValue.value} field. 
 */
public CUstreamBatchMemOpParams waitValue_value(@NativeType("cuuint32_t") int value){
  nwaitValue_value(address(),value);
  return this;
}
