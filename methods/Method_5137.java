@Override public void endTracks(){
  Format[] sampleFormats=new Format[bindingTrackOutputs.size()];
  for (int i=0; i < bindingTrackOutputs.size(); i++) {
    sampleFormats[i]=bindingTrackOutputs.valueAt(i).sampleFormat;
  }
  this.sampleFormats=sampleFormats;
}
