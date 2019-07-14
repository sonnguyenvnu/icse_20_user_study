@Override public MediaPeriod createPeriod(MediaPeriodId id,Allocator allocator,long startPositionUs){
  MediaPeriod[] periods=new MediaPeriod[mediaSources.length];
  int periodIndex=timelines[0].getIndexOfPeriod(id.periodUid);
  for (int i=0; i < periods.length; i++) {
    MediaPeriodId childMediaPeriodId=id.copyWithPeriodUid(timelines[i].getUidOfPeriod(periodIndex));
    periods[i]=mediaSources[i].createPeriod(childMediaPeriodId,allocator,startPositionUs);
  }
  return new MergingMediaPeriod(compositeSequenceableLoaderFactory,periods);
}
