/** 
 * Loads the optimum tiles for display at the current scale and translate, so the screen can be filled with tiles that are at least as high resolution as the screen. Frees up bitmaps that are now off the screen.
 * @param load Whether to load the new tiles needed. Use false while scrolling/panning for performance.
 */
private void refreshRequiredTiles(boolean load){
  if (decoder == null || tileMap == null) {
    return;
  }
  int sampleSize=Math.min(fullImageSampleSize,calculateInSampleSize(scale));
  for (  Map.Entry<Integer,List<Tile>> tileMapEntry : tileMap.entrySet()) {
    for (    Tile tile : tileMapEntry.getValue()) {
      if (tile.sampleSize < sampleSize || (tile.sampleSize > sampleSize && tile.sampleSize != fullImageSampleSize)) {
        tile.visible=false;
        if (tile.bitmap != null) {
          tile.bitmap.recycle();
          tile.bitmap=null;
        }
      }
      if (tile.sampleSize == sampleSize) {
        if (tileVisible(tile)) {
          tile.visible=true;
          if (!tile.loading && tile.bitmap == null && load) {
            TileLoadTask task=new TileLoadTask(this,decoder,tile);
            execute(task);
          }
        }
 else         if (tile.sampleSize != fullImageSampleSize) {
          tile.visible=false;
          if (tile.bitmap != null) {
            tile.bitmap.recycle();
            tile.bitmap=null;
          }
        }
      }
 else       if (tile.sampleSize == fullImageSampleSize) {
        tile.visible=true;
      }
    }
  }
}
