/** 
 * Returns a  {@link ByteBuffer} view of the {@code achFormatHint} field. 
 */
@NativeType("char[9]") public ByteBuffer achFormatHint(){
  return nachFormatHint(address());
}
