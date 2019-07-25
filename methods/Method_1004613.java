/** 
 * refresh the infowindow drawing. Must be called every time the view changes (drag, zoom,...). Best practice is to call this method in the draw method of its overlay.
 */
public void draw(){
  if (!mIsVisible)   return;
  try {
    MapView.LayoutParams lp=new MapView.LayoutParams(MapView.LayoutParams.WRAP_CONTENT,MapView.LayoutParams.WRAP_CONTENT,mPosition,MapView.LayoutParams.BOTTOM_CENTER,mOffsetX,mOffsetY);
    mMapView.updateViewLayout(mView,lp);
  }
 catch (  Exception e) {
    if (MapSnapshot.isUIThread()) {
      throw e;
    }
  }
}
