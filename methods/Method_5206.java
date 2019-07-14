private ChunkSampleStream<DashChunkSource> buildSampleStream(TrackGroupInfo trackGroupInfo,TrackSelection selection,long positionUs){
  int embeddedTrackCount=0;
  int[] embeddedTrackTypes=new int[2];
  Format[] embeddedTrackFormats=new Format[2];
  boolean enableEventMessageTrack=trackGroupInfo.embeddedEventMessageTrackGroupIndex != C.INDEX_UNSET;
  if (enableEventMessageTrack) {
    embeddedTrackFormats[embeddedTrackCount]=trackGroups.get(trackGroupInfo.embeddedEventMessageTrackGroupIndex).getFormat(0);
    embeddedTrackTypes[embeddedTrackCount++]=C.TRACK_TYPE_METADATA;
  }
  boolean enableCea608Track=trackGroupInfo.embeddedCea608TrackGroupIndex != C.INDEX_UNSET;
  if (enableCea608Track) {
    embeddedTrackFormats[embeddedTrackCount]=trackGroups.get(trackGroupInfo.embeddedCea608TrackGroupIndex).getFormat(0);
    embeddedTrackTypes[embeddedTrackCount++]=C.TRACK_TYPE_TEXT;
  }
  if (embeddedTrackCount < embeddedTrackTypes.length) {
    embeddedTrackFormats=Arrays.copyOf(embeddedTrackFormats,embeddedTrackCount);
    embeddedTrackTypes=Arrays.copyOf(embeddedTrackTypes,embeddedTrackCount);
  }
  PlayerTrackEmsgHandler trackPlayerEmsgHandler=manifest.dynamic && enableEventMessageTrack ? playerEmsgHandler.newPlayerTrackEmsgHandler() : null;
  DashChunkSource chunkSource=chunkSourceFactory.createDashChunkSource(manifestLoaderErrorThrower,manifest,periodIndex,trackGroupInfo.adaptationSetIndices,selection,trackGroupInfo.trackType,elapsedRealtimeOffsetMs,enableEventMessageTrack,enableCea608Track,trackPlayerEmsgHandler,transferListener);
  ChunkSampleStream<DashChunkSource> stream=new ChunkSampleStream<>(trackGroupInfo.trackType,embeddedTrackTypes,embeddedTrackFormats,chunkSource,this,allocator,positionUs,loadErrorHandlingPolicy,eventDispatcher);
synchronized (this) {
    trackEmsgHandlerBySampleStream.put(stream,trackPlayerEmsgHandler);
  }
  return stream;
}
