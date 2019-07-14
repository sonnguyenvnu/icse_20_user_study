/** 
 * Sets the specified value to the  {@code writeValue.value64} field. 
 */
public CUstreamBatchMemOpParams writeValue_value64(@NativeType("cuuint64_t") long value){
  nwriteValue_value64(address(),value);
  return this;
}
