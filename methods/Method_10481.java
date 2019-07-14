/** 
 * Chooses the optimal preview size based on  {@link #mPreviewSizes} and the surface size.
 * @return The picked size for camera preview.
 */
private Size chooseOptimalSize(){
  int surfaceLonger, surfaceShorter;
  final int surfaceWidth=mPreview.getWidth();
  final int surfaceHeight=mPreview.getHeight();
  if (surfaceWidth < surfaceHeight) {
    surfaceLonger=surfaceHeight;
    surfaceShorter=surfaceWidth;
  }
 else {
    surfaceLonger=surfaceWidth;
    surfaceShorter=surfaceHeight;
  }
  SortedSet<Size> candidates=mPreviewSizes.sizes(mAspectRatio);
  for (  Size size : candidates) {
    if (size.getWidth() >= surfaceLonger && size.getHeight() >= surfaceShorter) {
      return size;
    }
  }
  return candidates.last();
}
