/** 
 * Returns a copy of the raw value of the field.
 * @return the value of the field, in the byte order of the field.
 */
public byte[] _XXXXX_(){
  return BinaryFunctions.head(value,getBytesLength());
}