/** 
 * Returns a  {@link LongBuffer} view of the {@code pad} field. 
 */
@NativeType("cuuint64_t[6]") public LongBuffer pad(){
  return npad(address());
}
