private long getWrittenFrames(){
  return isInputPcm ? (writtenPcmBytes / outputPcmFrameSize) : writtenEncodedFrames;
}
