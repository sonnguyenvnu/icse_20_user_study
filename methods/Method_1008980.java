/** 
 * <p>Reads the class ID's value from a byte array by turning little-endian into big-endian.</p>
 * @param src The byte array to read from
 * @param offset The offset within the <var>src</var> byte array
 * @return A byte array containing the class ID.
 */
public byte[] read(final byte[] src,final int offset){
  bytes=new byte[16];
  bytes[0]=src[3 + offset];
  bytes[1]=src[2 + offset];
  bytes[2]=src[1 + offset];
  bytes[3]=src[0 + offset];
  bytes[4]=src[5 + offset];
  bytes[5]=src[4 + offset];
  bytes[6]=src[7 + offset];
  bytes[7]=src[6 + offset];
  for (int i=8; i < 16; i++)   bytes[i]=src[i + offset];
  return bytes;
}
