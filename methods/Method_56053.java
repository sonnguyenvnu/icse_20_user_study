/** 
 * Sets the specified value at the specified index of the  {@code pad} field. 
 */
public CUstreamBatchMemOpParams pad(int index,@NativeType("cuuint64_t") long value){
  npad(address(),index,value);
  return this;
}
