protected void createStsc(Track track,SampleTableBox stbl){
  SampleToChunkBox stsc=new SampleToChunkBox();
  stsc.setEntries(new LinkedList<>());
  long lastOffset;
  int lastChunkNumber=1;
  int lastSampleCount=0;
  int previousWritedChunkCount=-1;
  int samplesCount=track.getSamples().size();
  for (int a=0; a < samplesCount; a++) {
    Sample sample=track.getSamples().get(a);
    long offset=sample.getOffset();
    long size=sample.getSize();
    lastOffset=offset + size;
    lastSampleCount++;
    boolean write=false;
    if (a != samplesCount - 1) {
      Sample nextSample=track.getSamples().get(a + 1);
      if (lastOffset != nextSample.getOffset()) {
        write=true;
      }
    }
 else {
      write=true;
    }
    if (write) {
      if (previousWritedChunkCount != lastSampleCount) {
        stsc.getEntries().add(new SampleToChunkBox.Entry(lastChunkNumber,lastSampleCount,1));
        previousWritedChunkCount=lastSampleCount;
      }
      lastSampleCount=0;
      lastChunkNumber++;
    }
  }
  stbl.addBox(stsc);
}
