@Override public long selectTracks(TrackSelection[] selections,boolean[] mayRetainStreamFlags,SampleStream[] streams,boolean[] streamResetFlags,long positionUs){
  int[] streamChildIndices=new int[selections.length];
  int[] selectionChildIndices=new int[selections.length];
  for (int i=0; i < selections.length; i++) {
    streamChildIndices[i]=streams[i] == null ? C.INDEX_UNSET : streamPeriodIndices.get(streams[i]);
    selectionChildIndices[i]=C.INDEX_UNSET;
    if (selections[i] != null) {
      TrackGroup trackGroup=selections[i].getTrackGroup();
      for (int j=0; j < periods.length; j++) {
        if (periods[j].getTrackGroups().indexOf(trackGroup) != C.INDEX_UNSET) {
          selectionChildIndices[i]=j;
          break;
        }
      }
    }
  }
  streamPeriodIndices.clear();
  SampleStream[] newStreams=new SampleStream[selections.length];
  SampleStream[] childStreams=new SampleStream[selections.length];
  TrackSelection[] childSelections=new TrackSelection[selections.length];
  ArrayList<MediaPeriod> enabledPeriodsList=new ArrayList<>(periods.length);
  for (int i=0; i < periods.length; i++) {
    for (int j=0; j < selections.length; j++) {
      childStreams[j]=streamChildIndices[j] == i ? streams[j] : null;
      childSelections[j]=selectionChildIndices[j] == i ? selections[j] : null;
    }
    long selectPositionUs=periods[i].selectTracks(childSelections,mayRetainStreamFlags,childStreams,streamResetFlags,positionUs);
    if (i == 0) {
      positionUs=selectPositionUs;
    }
 else     if (selectPositionUs != positionUs) {
      throw new IllegalStateException("Children enabled at different positions.");
    }
    boolean periodEnabled=false;
    for (int j=0; j < selections.length; j++) {
      if (selectionChildIndices[j] == i) {
        Assertions.checkState(childStreams[j] != null);
        newStreams[j]=childStreams[j];
        periodEnabled=true;
        streamPeriodIndices.put(childStreams[j],i);
      }
 else       if (streamChildIndices[j] == i) {
        Assertions.checkState(childStreams[j] == null);
      }
    }
    if (periodEnabled) {
      enabledPeriodsList.add(periods[i]);
    }
  }
  System.arraycopy(newStreams,0,streams,0,newStreams.length);
  enabledPeriods=new MediaPeriod[enabledPeriodsList.size()];
  enabledPeriodsList.toArray(enabledPeriods);
  compositeSequenceableLoader=compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(enabledPeriods);
  return positionUs;
}
