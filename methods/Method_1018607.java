private CompletableFuture<AsyncReader> skip(long skip){
  long available=(long)bytesReady();
  if (skip <= available) {
    index+=(int)skip;
    return CompletableFuture.completedFuture(this);
  }
  long toRead=Math.min(available,skip);
  long toSkipAfterThisChunk=skip - toRead;
  long finalOffset=globalIndex + skip;
  long finalInternalIndex=finalOffset % Chunk.MAX_SIZE;
  long startOfTargetChunk=finalOffset - finalInternalIndex;
  long chunksToSkip=toSkipAfterThisChunk / Chunk.MAX_SIZE;
  int truncateTo=(int)Math.min(Chunk.MAX_SIZE,totalLength - startOfTargetChunk);
  return getSubsequentMetadata(nextChunkPointer,chunksToSkip).thenCompose(access -> getChunk(access,truncateTo)).thenApply(p -> new LazyInputStreamCombiner(version,finalOffset,p.left,p.right.getLocation(),originalChunk,originalNextPointer.getLocation(),network,random,baseKey,totalLength,x -> {
  }
)).thenCompose(reader -> reader.skip(finalInternalIndex));
}
