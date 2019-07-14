private void onFinalImageSet(){
  FLog.v(getLogTag(),"onFinalImageSet: view %x",this.hashCode());
  if (!mZoomableController.isEnabled() && mZoomingEnabled) {
    mZoomableController.setEnabled(true);
    updateZoomableControllerBounds();
  }
}
