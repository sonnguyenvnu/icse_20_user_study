/** 
 * Sets scale and translate ready for the next draw.
 */
private void preDraw(){
  if (getWidth() == 0 || getHeight() == 0 || sWidth <= 0 || sHeight <= 0) {
    return;
  }
  if (sPendingCenter != null && pendingScale != null) {
    scale=pendingScale;
    if (vTranslate == null) {
      vTranslate=new PointF();
    }
    vTranslate.x=(getWidth() / 2) - (scale * sPendingCenter.x);
    vTranslate.y=(getHeight() / 2) - (scale * sPendingCenter.y);
    sPendingCenter=null;
    pendingScale=null;
    fitToBounds(true);
    refreshRequiredTiles(true);
  }
  fitToBounds(false);
}
