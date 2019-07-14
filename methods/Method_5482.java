@Override public void updateManifest(SsManifest newManifest){
  StreamElement currentElement=manifest.streamElements[streamElementIndex];
  int currentElementChunkCount=currentElement.chunkCount;
  StreamElement newElement=newManifest.streamElements[streamElementIndex];
  if (currentElementChunkCount == 0 || newElement.chunkCount == 0) {
    currentManifestChunkOffset+=currentElementChunkCount;
  }
 else {
    long currentElementEndTimeUs=currentElement.getStartTimeUs(currentElementChunkCount - 1) + currentElement.getChunkDurationUs(currentElementChunkCount - 1);
    long newElementStartTimeUs=newElement.getStartTimeUs(0);
    if (currentElementEndTimeUs <= newElementStartTimeUs) {
      currentManifestChunkOffset+=currentElementChunkCount;
    }
 else {
      currentManifestChunkOffset+=currentElement.getChunkIndex(newElementStartTimeUs);
    }
  }
  manifest=newManifest;
}
