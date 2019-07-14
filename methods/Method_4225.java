private static boolean isLittleEndianFrameHeader(byte[] frameHeader){
  return frameHeader[0] == FIRST_BYTE_LE || frameHeader[0] == FIRST_BYTE_14B_LE;
}
