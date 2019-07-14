private ChunkSampleStream<SsChunkSource> buildSampleStream(TrackSelection selection,long positionUs){
  int streamElementIndex=trackGroups.indexOf(selection.getTrackGroup());
  SsChunkSource chunkSource=chunkSourceFactory.createChunkSource(manifestLoaderErrorThrower,manifest,streamElementIndex,selection,transferListener);
  return new ChunkSampleStream<>(manifest.streamElements[streamElementIndex].type,null,null,chunkSource,this,allocator,positionUs,loadErrorHandlingPolicy,eventDispatcher);
}
