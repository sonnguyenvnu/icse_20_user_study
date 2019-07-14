private void selectNewStreams(TrackSelection[] selections,SampleStream[] streams,boolean[] streamResetFlags,long positionUs,int[] streamIndexToTrackGroupIndex){
  for (int i=0; i < selections.length; i++) {
    if (streams[i] == null && selections[i] != null) {
      streamResetFlags[i]=true;
      int trackGroupIndex=streamIndexToTrackGroupIndex[i];
      TrackGroupInfo trackGroupInfo=trackGroupInfos[trackGroupIndex];
      if (trackGroupInfo.trackGroupCategory == TrackGroupInfo.CATEGORY_PRIMARY) {
        streams[i]=buildSampleStream(trackGroupInfo,selections[i],positionUs);
      }
 else       if (trackGroupInfo.trackGroupCategory == TrackGroupInfo.CATEGORY_MANIFEST_EVENTS) {
        EventStream eventStream=eventStreams.get(trackGroupInfo.eventStreamGroupIndex);
        Format format=selections[i].getTrackGroup().getFormat(0);
        streams[i]=new EventSampleStream(eventStream,format,manifest.dynamic);
      }
    }
  }
  for (int i=0; i < selections.length; i++) {
    if (streams[i] == null && selections[i] != null) {
      int trackGroupIndex=streamIndexToTrackGroupIndex[i];
      TrackGroupInfo trackGroupInfo=trackGroupInfos[trackGroupIndex];
      if (trackGroupInfo.trackGroupCategory == TrackGroupInfo.CATEGORY_EMBEDDED) {
        int primaryStreamIndex=getPrimaryStreamIndex(i,streamIndexToTrackGroupIndex);
        if (primaryStreamIndex == C.INDEX_UNSET) {
          streams[i]=new EmptySampleStream();
        }
 else {
          streams[i]=((ChunkSampleStream)streams[primaryStreamIndex]).selectEmbeddedTrack(positionUs,trackGroupInfo.trackType);
        }
      }
    }
  }
}
