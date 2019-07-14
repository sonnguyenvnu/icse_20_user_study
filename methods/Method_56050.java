/** 
 * Sets the specified value to the  {@code writeValue.value} field. 
 */
public CUstreamBatchMemOpParams writeValue_value(@NativeType("cuuint32_t") int value){
  nwriteValue_value(address(),value);
  return this;
}
