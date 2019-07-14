private static void addSegmentsForAdaptationSet(DataSource dataSource,AdaptationSet adaptationSet,long periodStartUs,long periodDurationUs,boolean allowIncompleteList,ArrayList<Segment> out) throws IOException, InterruptedException {
  for (int i=0; i < adaptationSet.representations.size(); i++) {
    Representation representation=adaptationSet.representations.get(i);
    DashSegmentIndex index;
    try {
      index=getSegmentIndex(dataSource,adaptationSet.type,representation);
      if (index == null) {
        throw new DownloadException("Missing segment index");
      }
    }
 catch (    IOException e) {
      if (!allowIncompleteList) {
        throw e;
      }
      continue;
    }
    int segmentCount=index.getSegmentCount(periodDurationUs);
    if (segmentCount == DashSegmentIndex.INDEX_UNBOUNDED) {
      throw new DownloadException("Unbounded segment index");
    }
    String baseUrl=representation.baseUrl;
    RangedUri initializationUri=representation.getInitializationUri();
    if (initializationUri != null) {
      addSegment(periodStartUs,baseUrl,initializationUri,out);
    }
    RangedUri indexUri=representation.getIndexUri();
    if (indexUri != null) {
      addSegment(periodStartUs,baseUrl,indexUri,out);
    }
    long firstSegmentNum=index.getFirstSegmentNum();
    long lastSegmentNum=firstSegmentNum + segmentCount - 1;
    for (long j=firstSegmentNum; j <= lastSegmentNum; j++) {
      addSegment(periodStartUs + index.getTimeUs(j),baseUrl,index.getSegmentUrl(j),out);
    }
  }
}
