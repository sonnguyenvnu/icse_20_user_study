/** 
 * Discards data from the buffer up to the first SPS, where  {@code data.position()} is interpretedas the length of the buffer. <p> When the method returns,  {@code data.position()} will contain the new length of the buffer. Ifthe buffer is not empty it is guaranteed to start with an SPS.
 * @param data Buffer containing start code delimited NAL units.
 */
public static void discardToSps(ByteBuffer data){
  int length=data.position();
  int consecutiveZeros=0;
  int offset=0;
  while (offset + 1 < length) {
    int value=data.get(offset) & 0xFF;
    if (consecutiveZeros == 3) {
      if (value == 1 && (data.get(offset + 1) & 0x1F) == H264_NAL_UNIT_TYPE_SPS) {
        ByteBuffer offsetData=data.duplicate();
        offsetData.position(offset - 3);
        offsetData.limit(length);
        data.position(0);
        data.put(offsetData);
        return;
      }
    }
 else     if (value == 0) {
      consecutiveZeros++;
    }
    if (value != 0) {
      consecutiveZeros=0;
    }
    offset++;
  }
  data.clear();
}
