private void stopScrolling(){
  scrolling=false;
  if (!scroll.isFinished()) {
    scroll.abortAnimation();
  }
  if (nextPhotoScrolling >= 0 && nextPhotoScrolling < currentObjects.size()) {
    stopedScrolling=true;
    nextImage=animateToItem=nextPhotoScrolling;
    animateToDX=(currentImage - nextPhotoScrolling) * (itemWidth + itemSpacing);
    animateToDXStart=drawDx;
    moveLineProgress=1.0f;
    nextPhotoScrolling=-1;
  }
  invalidate();
}
