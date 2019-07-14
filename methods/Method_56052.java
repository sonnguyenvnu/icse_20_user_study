/** 
 * Sets the specified value to the  {@code flushRemoteWrites.operation} field. 
 */
public CUstreamBatchMemOpParams flushRemoteWrites_operation(@NativeType("CUstreamBatchMemOpType") int value){
  nflushRemoteWrites_operation(address(),value);
  return this;
}
