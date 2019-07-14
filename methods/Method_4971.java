@Override public void render(long positionUs,long elapsedRealtimeUs) throws ExoPlaybackException {
  if (!inputStreamEnded && pendingMetadataCount < MAX_PENDING_METADATA_COUNT) {
    buffer.clear();
    int result=readSource(formatHolder,buffer,false);
    if (result == C.RESULT_BUFFER_READ) {
      if (buffer.isEndOfStream()) {
        inputStreamEnded=true;
      }
 else       if (buffer.isDecodeOnly()) {
      }
 else {
        buffer.subsampleOffsetUs=formatHolder.format.subsampleOffsetUs;
        buffer.flip();
        int index=(pendingMetadataIndex + pendingMetadataCount) % MAX_PENDING_METADATA_COUNT;
        Metadata metadata=decoder.decode(buffer);
        if (metadata != null) {
          pendingMetadata[index]=metadata;
          pendingMetadataTimestamps[index]=buffer.timeUs;
          pendingMetadataCount++;
        }
      }
    }
  }
  if (pendingMetadataCount > 0 && pendingMetadataTimestamps[pendingMetadataIndex] <= positionUs) {
    invokeRenderer(pendingMetadata[pendingMetadataIndex]);
    pendingMetadata[pendingMetadataIndex]=null;
    pendingMetadataIndex=(pendingMetadataIndex + 1) % MAX_PENDING_METADATA_COUNT;
    pendingMetadataCount--;
  }
}
