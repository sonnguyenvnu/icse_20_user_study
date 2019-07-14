/** 
 * Returns the number of output frames that can be read with  {@link #getOutput(ShortBuffer)}. 
 */
public int getFramesAvailable(){
  return outputFrameCount;
}
