/** 
 * Decodes the bytes with index  {@code [position(), position()+length}) in  {@code buffer}, as a UTF-8 string. <p>The current  {@code position} and {@code limit} of the specified {@code buffer} are not affected by this operation.</p>
 * @param buffer the {@link ByteBuffer} to decode
 * @param length the number of bytes to decode
 * @return the decoded {@link String}
 */
public static String memUTF8(ByteBuffer buffer,int length){
  return MultiReleaseTextDecoding.decodeUTF8(memAddress(buffer),length);
}
