@Override public MediaPeriod createPeriod(MediaPeriodId id,Allocator allocator,long startPositionUs){
  EventDispatcher eventDispatcher=createEventDispatcher(id);
  SsMediaPeriod period=new SsMediaPeriod(manifest,chunkSourceFactory,mediaTransferListener,compositeSequenceableLoaderFactory,loadErrorHandlingPolicy,eventDispatcher,manifestLoaderErrorThrower,allocator);
  mediaPeriods.add(period);
  return period;
}
