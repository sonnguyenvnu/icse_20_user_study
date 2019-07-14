protected void createStss(Track track,SampleTableBox stbl){
  long[] syncSamples=track.getSyncSamples();
  if (syncSamples != null && syncSamples.length > 0) {
    SyncSampleBox stss=new SyncSampleBox();
    stss.setSampleNumber(syncSamples);
    stbl.addBox(stss);
  }
}
