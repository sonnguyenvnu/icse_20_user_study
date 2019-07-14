/** 
 * Polls the pending output format queue for a given buffer timestamp. If a format is present, it is removed and returned. Otherwise returns  {@code null}. Subclasses should only call this method if they are taking over responsibility for output format propagation (e.g., when using video tunneling).
 */
protected final @Nullable Format updateOutputFormatForTime(long presentationTimeUs){
  Format format=formatQueue.pollFloor(presentationTimeUs);
  if (format != null) {
    outputFormat=format;
  }
  return format;
}
