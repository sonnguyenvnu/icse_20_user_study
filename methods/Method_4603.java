@Override public void seek(long position,long timeUs){
  synchronizedHeaderData=0;
  basisTimeUs=C.TIME_UNSET;
  samplesRead=0;
  sampleBytesRemaining=0;
}
