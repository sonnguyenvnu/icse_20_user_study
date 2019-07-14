@Nullable private static MlltSeeker maybeHandleSeekMetadata(Metadata metadata,long firstFramePosition){
  if (metadata != null) {
    int length=metadata.length();
    for (int i=0; i < length; i++) {
      Metadata.Entry entry=metadata.get(i);
      if (entry instanceof MlltFrame) {
        return MlltSeeker.create(firstFramePosition,(MlltFrame)entry);
      }
    }
  }
  return null;
}
