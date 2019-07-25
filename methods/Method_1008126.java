/** 
 * Must be called with increasing offset. See  {@link FieldHighlighter} for usage.
 */
@Override public int preceding(int offset){
  if (offset < lastPrecedingOffset) {
    throw new IllegalArgumentException("offset < lastPrecedingOffset: " + "usage doesn't look like UnifiedHighlighter");
  }
  if (offset > windowStart && offset < windowEnd) {
    innerStart=innerEnd;
    innerEnd=windowEnd;
  }
 else {
    windowStart=innerStart=mainBreak.preceding(offset);
    windowEnd=innerEnd=mainBreak.following(offset - 1);
    while (innerEnd - innerStart < maxLen) {
      int newEnd=mainBreak.following(innerEnd);
      if (newEnd == DONE || (newEnd - innerStart) > maxLen) {
        break;
      }
      windowEnd=innerEnd=newEnd;
    }
  }
  if (innerEnd - innerStart > maxLen) {
    if (offset - maxLen > innerStart) {
      innerStart=Math.max(innerStart,innerBreak.preceding(offset - maxLen));
    }
    int remaining=Math.max(0,maxLen - (offset - innerStart));
    if (offset + remaining < windowEnd) {
      innerEnd=Math.min(windowEnd,innerBreak.following(offset + remaining));
    }
  }
  lastPrecedingOffset=offset - 1;
  return innerStart;
}
