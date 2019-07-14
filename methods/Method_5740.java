/** 
 * Returns the length of the cached data block starting from the  {@code position} to the block endup to  {@code length} bytes. If the {@code position} isn't cached then -(the length of the gapto the next cached data up to  {@code length} bytes) is returned.
 * @param position The starting position of the data.
 * @param length The maximum length of the data to be returned.
 * @return the length of the cached or not cached data block length.
 */
public long getCachedBytesLength(long position,long length){
  SimpleCacheSpan span=getSpan(position);
  if (span.isHoleSpan()) {
    return -Math.min(span.isOpenEnded() ? Long.MAX_VALUE : span.length,length);
  }
  long queryEndPosition=position + length;
  long currentEndPosition=span.position + span.length;
  if (currentEndPosition < queryEndPosition) {
    for (    SimpleCacheSpan next : cachedSpans.tailSet(span,false)) {
      if (next.position > currentEndPosition) {
        break;
      }
      currentEndPosition=Math.max(currentEndPosition,next.position + next.length);
      if (currentEndPosition >= queryEndPosition) {
        break;
      }
    }
  }
  return Math.min(currentEndPosition - position,length);
}
