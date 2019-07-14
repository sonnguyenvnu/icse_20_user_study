private void onRelease(){
  FLog.v(getLogTag(),"onRelease: view %x",this.hashCode());
  mZoomableController.setEnabled(false);
}
