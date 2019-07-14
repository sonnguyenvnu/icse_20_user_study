private void calculateAverageFrameSize(ExtractorInput input) throws IOException, InterruptedException {
  if (hasCalculatedAverageFrameSize) {
    return;
  }
  averageFrameSize=C.LENGTH_UNSET;
  input.resetPeekPosition();
  if (input.getPosition() == 0) {
    peekId3Header(input);
  }
  int numValidFrames=0;
  long totalValidFramesSize=0;
  while (input.peekFully(scratch.data,0,2,true)) {
    scratch.setPosition(0);
    int syncBytes=scratch.readUnsignedShort();
    if (!AdtsReader.isAdtsSyncWord(syncBytes)) {
      numValidFrames=0;
      break;
    }
 else {
      if (!input.peekFully(scratch.data,0,4,true)) {
        break;
      }
      scratchBits.setPosition(14);
      int currentFrameSize=scratchBits.readBits(13);
      if (currentFrameSize <= 6) {
        hasCalculatedAverageFrameSize=true;
        throw new ParserException("Malformed ADTS stream");
      }
      totalValidFramesSize+=currentFrameSize;
      if (++numValidFrames == NUM_FRAMES_FOR_AVERAGE_FRAME_SIZE) {
        break;
      }
      if (!input.advancePeekPosition(currentFrameSize - 6,true)) {
        break;
      }
    }
  }
  input.resetPeekPosition();
  if (numValidFrames > 0) {
    averageFrameSize=(int)(totalValidFramesSize / numValidFrames);
  }
 else {
    averageFrameSize=C.LENGTH_UNSET;
  }
  hasCalculatedAverageFrameSize=true;
}
