public void setMoveProgress(float progress){
  if (scrolling || animateToItem >= 0) {
    return;
  }
  if (progress > 0) {
    nextImage=currentImage - 1;
  }
 else {
    nextImage=currentImage + 1;
  }
  if (nextImage >= 0 && nextImage < currentPhotos.size()) {
    currentItemProgress=1.0f - Math.abs(progress);
  }
 else {
    currentItemProgress=1.0f;
  }
  nextItemProgress=1.0f - currentItemProgress;
  moving=progress != 0;
  invalidate();
  if (currentPhotos.isEmpty() || progress < 0 && currentImage == currentPhotos.size() - 1 || progress > 0 && currentImage == 0) {
    return;
  }
  drawDx=(int)(progress * (itemWidth + itemSpacing));
  fillImages(true,drawDx);
}
