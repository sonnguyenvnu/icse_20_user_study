public int getNumberOfFrames(){
  if (isXingFrame()) {
    int xingOffset=header.getXingOffset();
    byte flags=bytes[xingOffset + 7];
    if ((flags & 0x01) != 0) {
      return ((bytes[xingOffset + 8] & 0xFF) << 24) | ((bytes[xingOffset + 9] & 0xFF) << 16) | ((bytes[xingOffset + 10] & 0xFF) << 8) | (bytes[xingOffset + 11] & 0xFF);
    }
  }
 else   if (isVBRIFrame()) {
    int vbriOffset=header.getVBRIOffset();
    return ((bytes[vbriOffset + 14] & 0xFF) << 24) | ((bytes[vbriOffset + 15] & 0xFF) << 16) | ((bytes[vbriOffset + 16] & 0xFF) << 8) | (bytes[vbriOffset + 17] & 0xFF);
  }
  return -1;
}
