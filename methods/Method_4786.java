private void parsePayloadMux(ParsableBitArray data,int muxLengthBytes){
  int bitPosition=data.getPosition();
  if ((bitPosition & 0x07) == 0) {
    sampleDataBuffer.setPosition(bitPosition >> 3);
  }
 else {
    data.readBits(sampleDataBuffer.data,0,muxLengthBytes * 8);
    sampleDataBuffer.setPosition(0);
  }
  output.sampleData(sampleDataBuffer,muxLengthBytes);
  output.sampleMetadata(timeUs,C.BUFFER_FLAG_KEY_FRAME,muxLengthBytes,0,null);
  timeUs+=sampleDurationUs;
}
