private static ParsableBitArray getNormalizedFrameHeader(byte[] frameHeader){
  if (frameHeader[0] == FIRST_BYTE_BE) {
    return new ParsableBitArray(frameHeader);
  }
  frameHeader=Arrays.copyOf(frameHeader,frameHeader.length);
  if (isLittleEndianFrameHeader(frameHeader)) {
    for (int i=0; i < frameHeader.length - 1; i+=2) {
      byte temp=frameHeader[i];
      frameHeader[i]=frameHeader[i + 1];
      frameHeader[i + 1]=temp;
    }
  }
  ParsableBitArray frameBits=new ParsableBitArray(frameHeader);
  if (frameHeader[0] == (byte)(SYNC_VALUE_14B_BE >> 24)) {
    ParsableBitArray scratchBits=new ParsableBitArray(frameHeader);
    while (scratchBits.bitsLeft() >= 16) {
      scratchBits.skipBits(2);
      frameBits.putInt(scratchBits.readBits(14),14);
    }
  }
  frameBits.reset(frameHeader);
  return frameBits;
}
