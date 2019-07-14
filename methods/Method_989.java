/** 
 * Renders the specified frame. Only should be called on the rendering thread.
 * @param frameNumber the frame to render
 * @param bitmap the bitmap to render into
 */
public void renderFrame(int frameNumber,Bitmap bitmap){
  Canvas canvas=new Canvas(bitmap);
  canvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.SRC);
  int nextIndex;
  if (!isKeyFrame(frameNumber)) {
    nextIndex=prepareCanvasWithClosestCachedFrame(frameNumber - 1,canvas);
  }
 else {
    nextIndex=frameNumber;
  }
  for (int index=nextIndex; index < frameNumber; index++) {
    AnimatedDrawableFrameInfo frameInfo=mAnimatedDrawableBackend.getFrameInfo(index);
    DisposalMethod disposalMethod=frameInfo.disposalMethod;
    if (disposalMethod == DisposalMethod.DISPOSE_TO_PREVIOUS) {
      continue;
    }
    if (frameInfo.blendOperation == BlendOperation.NO_BLEND) {
      disposeToBackground(canvas,frameInfo);
    }
    mAnimatedDrawableBackend.renderFrame(index,canvas);
    mCallback.onIntermediateResult(index,bitmap);
    if (disposalMethod == DisposalMethod.DISPOSE_TO_BACKGROUND) {
      disposeToBackground(canvas,frameInfo);
    }
  }
  AnimatedDrawableFrameInfo frameInfo=mAnimatedDrawableBackend.getFrameInfo(frameNumber);
  if (frameInfo.blendOperation == BlendOperation.NO_BLEND) {
    disposeToBackground(canvas,frameInfo);
  }
  mAnimatedDrawableBackend.renderFrame(frameNumber,canvas);
}
