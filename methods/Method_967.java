/** 
 * Gets whether it has the decoded frame. This will only return true if the {@code ImageDecodeOptions} were configured to decode all frames at decode time.
 * @param index the index of the frame to get
 * @return true if the result has the decoded frame
 */
public synchronized boolean hasDecodedFrame(int index){
  return mDecodedFrames != null && mDecodedFrames.get(index) != null;
}
