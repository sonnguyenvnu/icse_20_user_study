/** 
 * Checks whether the base layer of tiles or full size bitmap is ready.
 */
private boolean isBaseLayerReady(){
  if (bitmap != null && !bitmapIsPreview) {
    return true;
  }
 else   if (tileMap != null) {
    boolean baseLayerReady=true;
    for (    Map.Entry<Integer,List<Tile>> tileMapEntry : tileMap.entrySet()) {
      if (tileMapEntry.getKey() == fullImageSampleSize) {
        for (        Tile tile : tileMapEntry.getValue()) {
          if (tile.loading || tile.bitmap == null) {
            baseLayerReady=false;
          }
        }
      }
    }
    return baseLayerReady;
  }
  return false;
}
