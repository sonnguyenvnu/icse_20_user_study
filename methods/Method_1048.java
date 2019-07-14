/** 
 * Measures the source, and sets the size based on them. Maintains aspect ratio of source, and ensures that screen is filled in at least one dimension. <p>Adapted from com.facebook.cameracore.common.RenderUtil#calculateFitRect
 * @param viewPortWidth the width of the display
 * @param viewPortHeight the height of the display
 * @param sourceWidth the width of the video
 * @param sourceHeight the height of the video
 */
private void scale(int viewPortWidth,int viewPortHeight,int sourceWidth,int sourceHeight){
  float inputRatio=((float)sourceWidth) / sourceHeight;
  float outputRatio=((float)viewPortWidth) / viewPortHeight;
  int scaledWidth=viewPortWidth;
  int scaledHeight=viewPortHeight;
  if (outputRatio > inputRatio) {
    scaledWidth=(int)(viewPortHeight * inputRatio);
    scaledHeight=viewPortHeight;
  }
 else   if (outputRatio < inputRatio) {
    scaledHeight=(int)(viewPortWidth / inputRatio);
    scaledWidth=viewPortWidth;
  }
  float scale=scaledWidth / (float)sourceWidth;
  mMidX=((viewPortWidth - scaledWidth) / 2f) / scale;
  mMidY=((viewPortHeight - scaledHeight) / 2f) / scale;
}
