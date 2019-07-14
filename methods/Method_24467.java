public boolean hasBuffers(){
  return bufferSource != null && bufferCache != null && 0 < bufferCache.size();
}
