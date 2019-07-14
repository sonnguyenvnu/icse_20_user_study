/** 
 * @see MemoryUtil#memUTF8(ByteBuffer,int,int) 
 */
static String decodeUTF8(long source,int length){
  if (length <= 0) {
    return "";
  }
  byte[] bytes=length <= ARRAY_TLC_SIZE ? ARRAY_TLC_BYTE.get() : new byte[length];
  memByteBuffer(source,length).get(bytes,0,length);
  return new String(bytes,0,length,StandardCharsets.UTF_8);
}
