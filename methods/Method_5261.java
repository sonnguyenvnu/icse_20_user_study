protected SegmentList buildSegmentList(RangedUri initialization,long timescale,long presentationTimeOffset,long startNumber,long duration,List<SegmentTimelineElement> timeline,List<RangedUri> segments){
  return new SegmentList(initialization,timescale,presentationTimeOffset,startNumber,duration,timeline,segments);
}
