/** 
 * Called on first draw when the view has dimensions. Calculates the initial sample size and starts async loading of the base layer image - the whole source subsampled as necessary.
 */
private synchronized void initialiseBaseLayer(@NonNull Point maxTileDimensions){
  debug("initialiseBaseLayer maxTileDimensions=%dx%d",maxTileDimensions.x,maxTileDimensions.y);
  satTemp=new ScaleAndTranslate(0f,new PointF(0,0));
  fitToBounds(true,satTemp);
  fullImageSampleSize=calculateInSampleSize(satTemp.scale);
  if (fullImageSampleSize > 1) {
    fullImageSampleSize/=2;
  }
  if (fullImageSampleSize == 1 && sRegion == null && sWidth() < maxTileDimensions.x && sHeight() < maxTileDimensions.y) {
    decoder.recycle();
    decoder=null;
    BitmapLoadTask task=new BitmapLoadTask(this,getContext(),bitmapDecoderFactory,uri,false);
    execute(task);
  }
 else {
    initialiseTileMap(maxTileDimensions);
    List<Tile> baseGrid=tileMap.get(fullImageSampleSize);
    for (    Tile baseTile : baseGrid) {
      TileLoadTask task=new TileLoadTask(this,decoder,baseTile);
      execute(task);
    }
    refreshRequiredTiles(true);
  }
}
