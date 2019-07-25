@Override public void draw(Canvas c,MapView osmv,boolean shadow){
  if (shadow)   return;
  if (!isEnabled())   return;
  if (mLastOverlay != null)   mLastOverlay.onDetach(osmv);
  mLastOverlay=getLatLonGrid(osmv);
  mLastOverlay.draw(c,osmv,shadow);
}
