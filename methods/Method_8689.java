private int getMinScrollX(){
  return -(currentPhotos.size() - currentImage - 1) * (itemWidth + itemSpacing * 2);
}
