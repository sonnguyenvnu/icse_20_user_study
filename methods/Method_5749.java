/** 
 * When provided with a byte offset, this method locates the cached region within which the offset falls, and returns the approximate end position in milliseconds of that region. If the byte offset does not fall within a cached region then  {@link #NOT_CACHED} is returned.If the cached region extends to the end of the stream,  {@link #CACHED_TO_END} is returned.
 * @param byteOffset The byte offset in the underlying stream.
 * @return The end position of the corresponding cache region, {@link #NOT_CACHED}, or {@link #CACHED_TO_END}.
 */
public synchronized int getRegionEndTimeMs(long byteOffset){
  lookupRegion.startOffset=byteOffset;
  Region floorRegion=regions.floor(lookupRegion);
  if (floorRegion == null || byteOffset > floorRegion.endOffset || floorRegion.endOffsetIndex == -1) {
    return NOT_CACHED;
  }
  int index=floorRegion.endOffsetIndex;
  if (index == chunkIndex.length - 1 && floorRegion.endOffset == (chunkIndex.offsets[index] + chunkIndex.sizes[index])) {
    return CACHED_TO_END;
  }
  long segmentFractionUs=(chunkIndex.durationsUs[index] * (floorRegion.endOffset - chunkIndex.offsets[index])) / chunkIndex.sizes[index];
  return (int)((chunkIndex.timesUs[index] + segmentFractionUs) / 1000);
}
