private static MediaChunk newMediaChunk(Format format,DataSource dataSource,Uri uri,String cacheKey,int chunkIndex,long chunkStartTimeUs,long chunkEndTimeUs,long chunkSeekTimeUs,int trackSelectionReason,Object trackSelectionData,ChunkExtractorWrapper extractorWrapper){
  DataSpec dataSpec=new DataSpec(uri,0,C.LENGTH_UNSET,cacheKey);
  long sampleOffsetUs=chunkStartTimeUs;
  return new ContainerMediaChunk(dataSource,dataSpec,format,trackSelectionReason,trackSelectionData,chunkStartTimeUs,chunkEndTimeUs,chunkSeekTimeUs,C.TIME_UNSET,chunkIndex,1,sampleOffsetUs,extractorWrapper);
}
