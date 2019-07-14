/** 
 * Returns the approximate number of bytes per frame for the current FLAC stream. 
 */
public long getApproxBytesPerFrame(){
  long approxBytesPerFrame;
  if (maxFrameSize > 0) {
    approxBytesPerFrame=((long)maxFrameSize + minFrameSize) / 2 + 1;
  }
 else {
    long blockSize=(minBlockSize == maxBlockSize && minBlockSize > 0) ? minBlockSize : 4096;
    approxBytesPerFrame=(blockSize * channels * bitsPerSample) / 8 + 64;
  }
  return approxBytesPerFrame;
}
