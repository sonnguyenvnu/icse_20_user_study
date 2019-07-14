private void renderImageDoesNotSupportScaling(Canvas canvas,AnimatedImageFrame frame){
  int frameWidth, frameHeight, xOffset, yOffset;
  if (mDownscaleFrameToDrawableDimensions) {
    final int fittedWidth=Math.min(frame.getWidth(),canvas.getWidth());
    final int fittedHeight=Math.min(frame.getHeight(),canvas.getHeight());
    final float scaleX=(float)frame.getWidth() / (float)fittedWidth;
    final float scaleY=(float)frame.getHeight() / (float)fittedHeight;
    final float scale=Math.max(scaleX,scaleY);
    frameWidth=(int)(frame.getWidth() / scale);
    frameHeight=(int)(frame.getHeight() / scale);
    xOffset=(int)(frame.getXOffset() / scale);
    yOffset=(int)(frame.getYOffset() / scale);
  }
 else {
    frameWidth=frame.getWidth();
    frameHeight=frame.getHeight();
    xOffset=frame.getXOffset();
    yOffset=frame.getYOffset();
  }
synchronized (this) {
    prepareTempBitmapForThisSize(frameWidth,frameHeight);
    frame.renderFrame(frameWidth,frameHeight,mTempBitmap);
    canvas.save();
    canvas.translate(xOffset,yOffset);
    canvas.drawBitmap(mTempBitmap,0,0,null);
    canvas.restore();
  }
}
