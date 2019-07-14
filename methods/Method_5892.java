/** 
 * Returns the maximum size for a decoded frame from the FLAC stream. 
 */
public int maxDecodedFrameSize(){
  return maxBlockSize * channels * (bitsPerSample / 8);
}
