/** 
 * Returns the smallest video size greater than or equal to a specified size that also satisfies the  {@link MediaCodec}'s width and height alignment requirements. <p> Must not be called if the device SDK version is less than 21.
 * @param width Width in pixels.
 * @param height Height in pixels.
 * @return The smallest video size greater than or equal to the specified size that also satisfiesthe  {@link MediaCodec}'s width and height alignment requirements, or null if not a video codec.
 */
@TargetApi(21) public Point alignVideoSizeV21(int width,int height){
  if (capabilities == null) {
    logNoSupport("align.caps");
    return null;
  }
  VideoCapabilities videoCapabilities=capabilities.getVideoCapabilities();
  if (videoCapabilities == null) {
    logNoSupport("align.vCaps");
    return null;
  }
  int widthAlignment=videoCapabilities.getWidthAlignment();
  int heightAlignment=videoCapabilities.getHeightAlignment();
  return new Point(Util.ceilDivide(width,widthAlignment) * widthAlignment,Util.ceilDivide(height,heightAlignment) * heightAlignment);
}
