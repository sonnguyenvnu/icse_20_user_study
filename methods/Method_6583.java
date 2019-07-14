public float getImageAspectRatio(){
  return imageOrientation % 180 != 0 ? drawRegion.height() / drawRegion.width() : drawRegion.width() / drawRegion.height();
}
