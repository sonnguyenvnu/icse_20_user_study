protected void updateZoomableControllerBounds(){
  getImageBounds(mImageBounds);
  getLimitBounds(mViewBounds);
  mZoomableController.setImageBounds(mImageBounds);
  mZoomableController.setViewBounds(mViewBounds);
  FLog.v(getLogTag(),"updateZoomableControllerBounds: view %x, view bounds: %s, image bounds: %s",this.hashCode(),mViewBounds,mImageBounds);
}
