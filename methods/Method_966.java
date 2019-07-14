/** 
 * Gets a decoded frame. This will only return non-null if the  {@code ImageDecodeOptions}were configured to decode all frames at decode time.
 * @param index the index of the frame to get
 * @return a reference to the preview bitmap which must be released by the caller when done ornull if there is no preview bitmap set
 */
public synchronized @Nullable CloseableReference<Bitmap> getDecodedFrame(int index){
  if (mDecodedFrames != null) {
    return CloseableReference.cloneOrNull(mDecodedFrames.get(index));
  }
  return null;
}
