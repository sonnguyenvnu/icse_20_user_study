private void processOutputFormat(MediaCodec codec,int width,int height){
  currentWidth=width;
  currentHeight=height;
  currentPixelWidthHeightRatio=pendingPixelWidthHeightRatio;
  if (Util.SDK_INT >= 21) {
    if (pendingRotationDegrees == 90 || pendingRotationDegrees == 270) {
      int rotatedHeight=currentWidth;
      currentWidth=currentHeight;
      currentHeight=rotatedHeight;
      currentPixelWidthHeightRatio=1 / currentPixelWidthHeightRatio;
    }
  }
 else {
    currentUnappliedRotationDegrees=pendingRotationDegrees;
  }
  codec.setVideoScalingMode(scalingMode);
}
