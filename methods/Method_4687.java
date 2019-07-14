private int getFlacFrameBlockSize(ParsableByteArray packet){
  int blockSizeCode=(packet.data[2] & 0xFF) >> 4;
switch (blockSizeCode) {
case 1:
    return 192;
case 2:
case 3:
case 4:
case 5:
  return 576 << (blockSizeCode - 2);
case 6:
case 7:
packet.skipBytes(FRAME_HEADER_SAMPLE_NUMBER_OFFSET);
packet.readUtf8EncodedLong();
int value=blockSizeCode == 6 ? packet.readUnsignedByte() : packet.readUnsignedShort();
packet.setPosition(0);
return value + 1;
case 8:
case 9:
case 10:
case 11:
case 12:
case 13:
case 14:
case 15:
return 256 << (blockSizeCode - 8);
default :
return -1;
}
}
