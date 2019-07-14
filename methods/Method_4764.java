@Override public void packetStarted(long pesTimeUs,@TsPayloadReader.Flags int flags){
  this.pesTimeUs=pesTimeUs;
  randomAccessIndicator|=(flags & FLAG_RANDOM_ACCESS_INDICATOR) != 0;
}
