@SuppressWarnings("SuspiciousNameCombination") private Size chooseOptimalSize(SortedSet<Size> sizes){
  if (!mPreview.isReady()) {
    return sizes.first();
  }
  int desiredWidth;
  int desiredHeight;
  final int surfaceWidth=mPreview.getWidth();
  final int surfaceHeight=mPreview.getHeight();
  if (isLandscape(mDisplayOrientation)) {
    desiredWidth=surfaceHeight;
    desiredHeight=surfaceWidth;
  }
 else {
    desiredWidth=surfaceWidth;
    desiredHeight=surfaceHeight;
  }
  Size result=null;
  for (  Size size : sizes) {
    if (desiredWidth <= size.getWidth() && desiredHeight <= size.getHeight()) {
      return size;
    }
    result=size;
  }
  return result;
}
