/** 
 * Attempts to set the read position to the specified sample index.
 * @param sampleIndex The sample index.
 * @return Whether the read position was set successfully. False is returned if the specifiedindex is smaller than the index of the first sample in the queue, or larger than the index of the next sample that will be written.
 */
public synchronized boolean setReadPosition(int sampleIndex){
  if (absoluteFirstIndex <= sampleIndex && sampleIndex <= absoluteFirstIndex + length) {
    readPosition=sampleIndex - absoluteFirstIndex;
    return true;
  }
  return false;
}
