protected SegmentTemplate buildSegmentTemplate(RangedUri initialization,long timescale,long presentationTimeOffset,long startNumber,long duration,List<SegmentTimelineElement> timeline,UrlTemplate initializationTemplate,UrlTemplate mediaTemplate){
  return new SegmentTemplate(initialization,timescale,presentationTimeOffset,startNumber,duration,timeline,initializationTemplate,mediaTemplate);
}
