private void updateMinMax(float scale){
  int maxW=(int)(centerImage.getImageWidth() * scale - getContainerViewWidth()) / 2;
  int maxH=(int)(centerImage.getImageHeight() * scale - getContainerViewHeight()) / 2;
  if (maxW > 0) {
    minX=-maxW;
    maxX=maxW;
  }
 else {
    minX=maxX=0;
  }
  if (maxH > 0) {
    minY=-maxH;
    maxY=maxH;
  }
 else {
    minY=maxY=0;
  }
}
