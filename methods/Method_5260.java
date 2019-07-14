protected SingleSegmentBase buildSingleSegmentBase(RangedUri initialization,long timescale,long presentationTimeOffset,long indexStart,long indexLength){
  return new SingleSegmentBase(initialization,timescale,presentationTimeOffset,indexStart,indexLength);
}
