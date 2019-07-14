private void handleZoom(boolean isZoomIn,Camera camera){
  Log.e("Camera","????????");
  Camera.Parameters params=camera.getParameters();
  if (params.isZoomSupported()) {
    int maxZoom=params.getMaxZoom();
    int zoom=params.getZoom();
    if (isZoomIn && zoom < maxZoom) {
      Log.e("Camera","??????zoom=" + zoom);
      zoom++;
    }
 else     if (zoom > 0) {
      Log.e("Camera","??????zoom=" + zoom);
      zoom--;
    }
    params.setZoom(zoom);
    camera.setParameters(params);
  }
 else {
    Log.i(TAG,"zoom not supported");
  }
}
