/** 
 * Processes a change in the output buffers.
 */
private void processOutputBuffersChanged(){
  if (Util.SDK_INT < 21) {
    outputBuffers=codec.getOutputBuffers();
  }
}
