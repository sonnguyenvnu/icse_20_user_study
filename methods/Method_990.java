/** 
 * Given a frame number, prepares the canvas to render based on the nearest cached frame at or before the frame. On return the canvas will be prepared as if the nearest cached frame had been rendered and disposed. The returned index is the next frame that needs to be composited onto the canvas.
 * @param previousFrameNumber the frame number that is ones less than the one we're rendering
 * @param canvas the canvas to prepare
 * @return the index of the the next frame to process
 */
private int prepareCanvasWithClosestCachedFrame(int previousFrameNumber,Canvas canvas){
  for (int index=previousFrameNumber; index >= 0; index--) {
    FrameNeededResult neededResult=isFrameNeededForRendering(index);
switch (neededResult) {
case REQUIRED:
      AnimatedDrawableFrameInfo frameInfo=mAnimatedDrawableBackend.getFrameInfo(index);
    CloseableReference<Bitmap> startBitmap=mCallback.getCachedBitmap(index);
  if (startBitmap != null) {
    try {
      canvas.drawBitmap(startBitmap.get(),0,0,null);
      if (frameInfo.disposalMethod == DisposalMethod.DISPOSE_TO_BACKGROUND) {
        disposeToBackground(canvas,frameInfo);
      }
      return index + 1;
    }
  finally {
      startBitmap.close();
    }
  }
 else {
    if (isKeyFrame(index)) {
      return index;
    }
 else {
      break;
    }
  }
case NOT_REQUIRED:
return index + 1;
case ABORT:
return index;
case SKIP:
default :
}
}
return 0;
}
