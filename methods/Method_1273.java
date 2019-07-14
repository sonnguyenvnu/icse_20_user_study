/** 
 * updateDebugOverlay updates the debug overlay. Subclasses of  {@link PipelineDraweeController}can override this method (and call <code>super</code>) to provide additional debug information.
 */
protected void updateDebugOverlay(@Nullable CloseableImage image,DebugControllerOverlayDrawable debugOverlay){
  debugOverlay.setControllerId(getId());
  final DraweeHierarchy draweeHierarchy=getHierarchy();
  ScaleType scaleType=null;
  if (draweeHierarchy != null) {
    final ScaleTypeDrawable scaleTypeDrawable=ScalingUtils.getActiveScaleTypeDrawable(draweeHierarchy.getTopLevelDrawable());
    scaleType=scaleTypeDrawable != null ? scaleTypeDrawable.getScaleType() : null;
  }
  debugOverlay.setScaleType(scaleType);
  debugOverlay.setOrigin(mDebugOverlayImageOriginListener.getImageOrigin());
  if (image != null) {
    debugOverlay.setDimensions(image.getWidth(),image.getHeight());
    debugOverlay.setImageSize(image.getSizeInBytes());
  }
 else {
    debugOverlay.reset();
  }
}
