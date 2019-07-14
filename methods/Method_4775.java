@Override public void packetStarted(long pesTimeUs,@TsPayloadReader.Flags int flags){
  if ((flags & FLAG_DATA_ALIGNMENT_INDICATOR) == 0) {
    return;
  }
  writingSample=true;
  sampleTimeUs=pesTimeUs;
  sampleSize=0;
  sampleBytesRead=0;
}
