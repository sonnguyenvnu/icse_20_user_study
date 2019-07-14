/** 
 * Returns a  {@link ByteBuffer} view of the {@code szDevice} field. 
 */
@NativeType("TCHAR[32]") public ByteBuffer szDevice(){
  return nszDevice(address());
}
