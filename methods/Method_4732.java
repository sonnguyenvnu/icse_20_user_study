@Override public boolean sniff(ExtractorInput input) throws IOException, InterruptedException {
  int startPosition=peekId3Header(input);
  int headerPosition=startPosition;
  int totalValidFramesSize=0;
  int validFramesCount=0;
  while (true) {
    input.peekFully(scratch.data,0,2);
    scratch.setPosition(0);
    int syncBytes=scratch.readUnsignedShort();
    if (!AdtsReader.isAdtsSyncWord(syncBytes)) {
      validFramesCount=0;
      totalValidFramesSize=0;
      input.resetPeekPosition();
      if (++headerPosition - startPosition >= MAX_SNIFF_BYTES) {
        return false;
      }
      input.advancePeekPosition(headerPosition);
    }
 else {
      if (++validFramesCount >= 4 && totalValidFramesSize > TsExtractor.TS_PACKET_SIZE) {
        return true;
      }
      input.peekFully(scratch.data,0,4);
      scratchBits.setPosition(14);
      int frameSize=scratchBits.readBits(13);
      if (frameSize <= 6) {
        return false;
      }
      input.advancePeekPosition(frameSize - 6);
      totalValidFramesSize+=frameSize;
    }
  }
}
