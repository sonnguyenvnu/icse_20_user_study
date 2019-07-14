private boolean canDecodeBuffer(){
  return !queuedInputBuffers.isEmpty() && availableOutputBufferCount > 0;
}
