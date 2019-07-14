private static void buildManifestEventTrackGroupInfos(List<EventStream> eventStreams,TrackGroup[] trackGroups,TrackGroupInfo[] trackGroupInfos,int existingTrackGroupCount){
  for (int i=0; i < eventStreams.size(); i++) {
    EventStream eventStream=eventStreams.get(i);
    Format format=Format.createSampleFormat(eventStream.id(),MimeTypes.APPLICATION_EMSG,null,Format.NO_VALUE,null);
    trackGroups[existingTrackGroupCount]=new TrackGroup(format);
    trackGroupInfos[existingTrackGroupCount++]=TrackGroupInfo.mpdEventTrack(i);
  }
}
