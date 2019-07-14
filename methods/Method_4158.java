/** 
 * Returns the offset relative to the buffer's position of the start of a TrueHD syncframe, or {@link C#INDEX_UNSET} if no syncframe was found. The buffer's position is not modified.
 * @param buffer The {@link ByteBuffer} within which to find a syncframe.
 * @return The offset relative to the buffer's position of the start of a TrueHD syncframe, or{@link C#INDEX_UNSET} if no syncframe was found.
 */
public static int findTrueHdSyncframeOffset(ByteBuffer buffer){
  int startIndex=buffer.position();
  int endIndex=buffer.limit() - TRUEHD_SYNCFRAME_PREFIX_LENGTH;
  for (int i=startIndex; i <= endIndex; i++) {
    if ((buffer.getInt(i + 4) & 0xFEFFFFFF) == 0xBA6F72F8) {
      return i - startIndex;
    }
  }
  return C.INDEX_UNSET;
}
