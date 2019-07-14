/** 
 * Seeks to given frame
 * @param index index of the frame
 * @throws IndexOutOfBoundsException if {@code index < 0 || index >= <number of frames>}
 */
public void seekToFrame(@IntRange(from=0) int index){
  mGifInfoHandle.seekToFrameGL(index);
}
