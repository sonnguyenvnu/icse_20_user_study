/** 
 * Reset all state before setting/changing image or setting new rotation.
 */
private void reset(boolean newImage){
  setScale(0f);
  scaleStart=0f;
  vTranslate=null;
  vTranslateStart=null;
  pendingScale=0f;
  sPendingCenter=null;
  sRequestedCenter=null;
  isZooming=false;
  isPanning=false;
  isQuickScaling=false;
  maxTouchCount=0;
  fullImageSampleSize=0;
  vCenterStart=null;
  vDistStart=0;
  quickScaleCenter=null;
  quickScaleLastDistance=0f;
  quickScaleLastPoint=null;
  quickScaleMoved=false;
  anim=null;
  satTemp=null;
  matrix=null;
  sRect=null;
  if (newImage) {
    uri=null;
    if (decoder != null) {
synchronized (decoderLock) {
        decoder.recycle();
        decoder=null;
      }
    }
    if (bitmap != null && !bitmapIsCached) {
      bitmap.recycle();
    }
    sWidth=0;
    sHeight=0;
    sOrientation=0;
    sRegion=null;
    pRegion=null;
    readySent=false;
    imageLoadedSent=false;
    bitmap=null;
    bitmapIsPreview=false;
    bitmapIsCached=false;
  }
  if (tileMap != null) {
    for (    Map.Entry<Integer,List<Tile>> tileMapEntry : tileMap.entrySet()) {
      for (      Tile tile : tileMapEntry.getValue()) {
        tile.visible=false;
        if (tile.bitmap != null) {
          tile.bitmap.recycle();
          tile.bitmap=null;
        }
      }
    }
    tileMap=null;
  }
  setGestureDetector(getContext());
}
