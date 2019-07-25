/** 
 * <p>Writes the class ID to a byte array in the little-endian format.</p>
 * @param dst The byte array to write to.
 * @param offset The offset within the <var>dst</var> byte array.
 * @exception ArrayStoreException if there is not enough room for the classID 16 bytes in the byte array after the <var>offset</var> position.
 */
public void write(final byte[] dst,final int offset) throws ArrayStoreException {
  if (dst.length < 16)   throw new ArrayStoreException("Destination byte[] must have room for at least 16 bytes, " + "but has a length of only " + dst.length + ".");
  dst[0 + offset]=bytes[3];
  dst[1 + offset]=bytes[2];
  dst[2 + offset]=bytes[1];
  dst[3 + offset]=bytes[0];
  dst[4 + offset]=bytes[5];
  dst[5 + offset]=bytes[4];
  dst[6 + offset]=bytes[7];
  dst[7 + offset]=bytes[6];
  for (int i=8; i < 16; i++)   dst[i + offset]=bytes[i];
}
