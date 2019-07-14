protected void createStco(Track track,SampleTableBox stbl){
  ArrayList<Long> chunksOffsets=new ArrayList<>();
  long lastOffset=-1;
  for (  Sample sample : track.getSamples()) {
    long offset=sample.getOffset();
    if (lastOffset != -1 && lastOffset != offset) {
      lastOffset=-1;
    }
    if (lastOffset == -1) {
      chunksOffsets.add(offset);
    }
    lastOffset=offset + sample.getSize();
  }
  long[] chunkOffsetsLong=new long[chunksOffsets.size()];
  for (int a=0; a < chunksOffsets.size(); a++) {
    chunkOffsetsLong[a]=chunksOffsets.get(a);
  }
  StaticChunkOffsetBox stco=new StaticChunkOffsetBox();
  stco.setChunkOffsets(chunkOffsetsLong);
  stbl.addBox(stco);
}
