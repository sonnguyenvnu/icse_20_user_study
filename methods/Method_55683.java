/** 
 * Creates a new direct ByteBuffer that starts at the specified memory address and has capacity equal to the null-terminated string starting at that address, up to a maximum of  {@code maxLength} bytes. A single \0 character will terminate the string. The returned buffer will NOT include the \0 byte.<p>This method is useful for reading ASCII and UTF8 encoded text.</p>
 * @param address   the starting memory address
 * @param maxLength the maximum string length, in bytes
 * @return the new ByteBuffer
 */
public static ByteBuffer memByteBufferNT1(long address,int maxLength){
  return memByteBuffer(address,memLengthNT1(address,maxLength));
}
