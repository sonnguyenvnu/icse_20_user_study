public void updateManifest(SsManifest manifest){
  this.manifest=manifest;
  for (  ChunkSampleStream<SsChunkSource> sampleStream : sampleStreams) {
    sampleStream.getChunkSource().updateManifest(manifest);
  }
  callback.onContinueLoadingRequested(this);
}
