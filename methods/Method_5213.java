@Override public MediaPeriod createPeriod(MediaPeriodId periodId,Allocator allocator,long startPositionUs){
  int periodIndex=(Integer)periodId.periodUid - firstPeriodId;
  EventDispatcher periodEventDispatcher=createEventDispatcher(periodId,manifest.getPeriod(periodIndex).startMs);
  DashMediaPeriod mediaPeriod=new DashMediaPeriod(firstPeriodId + periodIndex,manifest,periodIndex,chunkSourceFactory,mediaTransferListener,loadErrorHandlingPolicy,periodEventDispatcher,elapsedRealtimeOffsetMs,manifestLoadErrorThrower,allocator,compositeSequenceableLoaderFactory,playerEmsgCallback);
  periodsById.put(mediaPeriod.id,mediaPeriod);
  return mediaPeriod;
}
