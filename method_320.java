@Override public CompletableFuture<ServingState> _XXXXX_(ServingState state){
  return checkStreamCreated(() -> {
    long mTime=System.currentTimeMillis();
    byte[] streamMetadataKey=getStreamMetadataKey(streamId);
    byte[] streamMetadata=toStreamMetadata(state,mTime).toByteArray();
    return store.rPut(streamMetadataKey,streamMetadata,revision).thenApplyAsync(newRev -> {
      this.servingState=state;
      this.mTime=mTime;
      this.revision=newRev;
      return state;
    }
,executor);
  }
);
}