@Override public void onReady(){
  float result=0.5f;
  int imageWidth=mImageView.getSWidth();
  int imageHeight=mImageView.getSHeight();
  int viewWidth=mImageView.getWidth();
  int viewHeight=mImageView.getHeight();
  boolean hasZeroValue=false;
  if (imageWidth == 0 || imageHeight == 0 || viewWidth == 0 || viewHeight == 0) {
    result=0.5f;
    hasZeroValue=true;
  }
  if (!hasZeroValue) {
    if (imageWidth <= imageHeight) {
      result=(float)viewWidth / imageWidth;
    }
 else {
      result=(float)viewHeight / imageHeight;
    }
  }
  if (!hasZeroValue && (float)imageHeight / imageWidth > LONG_IMAGE_SIZE_RATIO) {
    mImageView.animateScaleAndCenter(result,new PointF(imageWidth / 2,0)).withEasing(SubsamplingScaleImageView.EASE_OUT_QUAD).start();
  }
  if (Math.abs(result - 0.1) < 0.2f) {
    result+=0.2f;
  }
  if (mInitScaleType == BigImageView.INIT_SCALE_TYPE_CUSTOM) {
    float maxScale=Math.max((float)viewWidth / imageWidth,(float)viewHeight / imageHeight);
    if (maxScale > 1) {
      mImageView.setMinScale(1);
      float defaultMaxScale=mImageView.getMaxScale();
      mImageView.setMaxScale(Math.max(defaultMaxScale,maxScale * 1.2F));
    }
 else {
      float minScale=Math.min((float)viewWidth / imageWidth,(float)viewHeight / imageHeight);
      mImageView.setMinScale(minScale);
    }
    mImageView.setScaleAndCenter(maxScale,new PointF(imageWidth / 2,imageHeight / 2));
  }
  mImageView.setDoubleTapZoomScale(result);
}
