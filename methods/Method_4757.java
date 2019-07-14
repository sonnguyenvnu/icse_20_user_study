@Override public void packetStarted(long pesTimeUs,@TsPayloadReader.Flags int flags){
  if ((flags & FLAG_DATA_ALIGNMENT_INDICATOR) == 0) {
    return;
  }
  writingSample=true;
  sampleTimeUs=pesTimeUs;
  sampleBytesWritten=0;
  bytesToCheck=2;
}
