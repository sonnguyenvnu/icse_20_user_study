/** 
 * Decodes the bytes with index  {@code [offset, offset+length}) in  {@code buffer}, as a UTF-8 string. <p>The current  {@code position} and {@code limit} of the specified {@code buffer} are not affected by this operation.</p>
 * @param buffer the {@link ByteBuffer} to decode
 * @param length the number of bytes to decode
 * @param offset the offset at which to start decoding.
 * @return the decoded {@link String}
 */
public static String memUTF8(ByteBuffer buffer,int length,int offset){
  return MultiReleaseTextDecoding.decodeUTF8(memAddress(buffer,offset),length);
}
