private int peekId3Header(ExtractorInput input) throws IOException, InterruptedException {
  int firstFramePosition=0;
  while (true) {
    input.peekFully(scratch.data,0,10);
    scratch.setPosition(0);
    if (scratch.readUnsignedInt24() != ID3_TAG) {
      break;
    }
    scratch.skipBytes(3);
    int length=scratch.readSynchSafeInt();
    firstFramePosition+=10 + length;
    input.advancePeekPosition(length);
  }
  input.resetPeekPosition();
  input.advancePeekPosition(firstFramePosition);
  if (this.firstFramePosition == C.POSITION_UNSET) {
    this.firstFramePosition=firstFramePosition;
  }
  return firstFramePosition;
}
