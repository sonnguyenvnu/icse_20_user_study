/** 
 * Checks whether the specified frame number is outside the range inclusive of both start and end. If start <= end, start is within, end is within, and everything in between is within. If start > end, start is within, end is within, everything less than start is within and everything greater than end is within. This behavior is useful for handling the wrapping case.
 * @param startFrame the start frame
 * @param endFrame the end frame
 * @param frameNumber the frame number
 * @return whether the frame is outside the range of [start, end]
 */
public static boolean isOutsideRange(int startFrame,int endFrame,int frameNumber){
  if (startFrame == -1 || endFrame == -1) {
    return true;
  }
  boolean outsideRange;
  if (startFrame <= endFrame) {
    outsideRange=frameNumber < startFrame || frameNumber > endFrame;
  }
 else {
    outsideRange=frameNumber < startFrame && frameNumber > endFrame;
  }
  return outsideRange;
}
