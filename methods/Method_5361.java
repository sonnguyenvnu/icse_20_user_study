private HlsSampleStreamWrapper buildSampleStreamWrapper(int trackType,HlsUrl[] variants,Format muxedAudioFormat,List<Format> muxedCaptionFormats,long positionUs){
  HlsChunkSource defaultChunkSource=new HlsChunkSource(extractorFactory,playlistTracker,variants,dataSourceFactory,mediaTransferListener,timestampAdjusterProvider,muxedCaptionFormats);
  return new HlsSampleStreamWrapper(trackType,this,defaultChunkSource,allocator,positionUs,muxedAudioFormat,loadErrorHandlingPolicy,eventDispatcher);
}
