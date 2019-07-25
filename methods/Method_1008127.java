/** 
 * Can be invoked only after a call to preceding(offset+1). See  {@link FieldHighlighter} for usage.
 */
@Override public int following(int offset){
  if (offset != lastPrecedingOffset || innerEnd == -1) {
    throw new IllegalArgumentException("offset != lastPrecedingOffset: " + "usage doesn't look like UnifiedHighlighter");
  }
  return innerEnd;
}
