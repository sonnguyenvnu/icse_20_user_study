@Override public UpdateSketch rebuild(){
  final int lgNomLongs=getLgNomLongs();
  final int preambleLongs=mem_.getByte(PREAMBLE_LONGS_BYTE) & 0X3F;
  if (getRetainedEntries(true) > (1 << lgNomLongs)) {
    quickSelectAndRebuild(mem_,preambleLongs,lgNomLongs);
  }
  return this;
}
