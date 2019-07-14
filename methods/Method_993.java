/** 
 * Adjusts the frame duration array to respect logic for minimum frame duration time.
 * @param frameDurationMs the frame duration array
 */
public void fixFrameDurations(int[] frameDurationMs){
  for (int i=0; i < frameDurationMs.length; i++) {
    if (frameDurationMs[i] < MIN_FRAME_DURATION_MS) {
      frameDurationMs[i]=FRAME_DURATION_MS_FOR_MIN;
    }
  }
}
