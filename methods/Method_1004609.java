private void draw(){
  mBitmap=Bitmap.createBitmap(mProjection.getWidth(),mProjection.getHeight(),Bitmap.Config.ARGB_8888);
  final Canvas canvas=new Canvas(mBitmap);
  mProjection.save(canvas,true,false);
  mTilesOverlay.drawTiles(canvas,mProjection,mProjection.getZoomLevel(),mViewPort);
  if (mOverlays != null) {
    for (    final Overlay overlay : mOverlays) {
      if (overlay != null && overlay.isEnabled()) {
        overlay.draw(canvas,mProjection);
      }
    }
  }
  mProjection.restore(canvas,false);
}
