protected Chunk newMediaChunk(RepresentationHolder representationHolder,DataSource dataSource,int trackType,Format trackFormat,int trackSelectionReason,Object trackSelectionData,long firstSegmentNum,int maxSegmentCount,long seekTimeUs){
  Representation representation=representationHolder.representation;
  long startTimeUs=representationHolder.getSegmentStartTimeUs(firstSegmentNum);
  RangedUri segmentUri=representationHolder.getSegmentUrl(firstSegmentNum);
  String baseUrl=representation.baseUrl;
  if (representationHolder.extractorWrapper == null) {
    long endTimeUs=representationHolder.getSegmentEndTimeUs(firstSegmentNum);
    DataSpec dataSpec=new DataSpec(segmentUri.resolveUri(baseUrl),segmentUri.start,segmentUri.length,representation.getCacheKey());
    return new SingleSampleMediaChunk(dataSource,dataSpec,trackFormat,trackSelectionReason,trackSelectionData,startTimeUs,endTimeUs,firstSegmentNum,trackType,trackFormat);
  }
 else {
    int segmentCount=1;
    for (int i=1; i < maxSegmentCount; i++) {
      RangedUri nextSegmentUri=representationHolder.getSegmentUrl(firstSegmentNum + i);
      RangedUri mergedSegmentUri=segmentUri.attemptMerge(nextSegmentUri,baseUrl);
      if (mergedSegmentUri == null) {
        break;
      }
      segmentUri=mergedSegmentUri;
      segmentCount++;
    }
    long endTimeUs=representationHolder.getSegmentEndTimeUs(firstSegmentNum + segmentCount - 1);
    long periodDurationUs=representationHolder.periodDurationUs;
    long clippedEndTimeUs=periodDurationUs != C.TIME_UNSET && periodDurationUs <= endTimeUs ? periodDurationUs : C.TIME_UNSET;
    DataSpec dataSpec=new DataSpec(segmentUri.resolveUri(baseUrl),segmentUri.start,segmentUri.length,representation.getCacheKey());
    long sampleOffsetUs=-representation.presentationTimeOffsetUs;
    return new ContainerMediaChunk(dataSource,dataSpec,trackFormat,trackSelectionReason,trackSelectionData,startTimeUs,endTimeUs,seekTimeUs,clippedEndTimeUs,firstSegmentNum,segmentCount,sampleOffsetUs,representationHolder.extractorWrapper);
  }
}
