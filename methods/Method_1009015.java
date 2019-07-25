/** 
 * set the ByteField's current value and write it to a byte array
 * @param value to be set
 * @param data the byte array to write the value to
 * @exception ArrayIndexOutOfBoundsException if the offset is outof the byte array's range
 */
public void set(final byte value,final byte[] data) throws ArrayIndexOutOfBoundsException {
  set(value);
  writeToBytes(data);
}
