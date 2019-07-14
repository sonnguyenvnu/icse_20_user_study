/** 
 * Once source image and view dimensions are known, creates a map of sample size to tile grid.
 */
private void initialiseTileMap(Point maxTileDimensions){
  debug("initialiseTileMap maxTileDimensions=%dx%d",maxTileDimensions.x,maxTileDimensions.y);
  this.tileMap=new LinkedHashMap<>();
  int sampleSize=fullImageSampleSize;
  int xTiles=1;
  int yTiles=1;
  while (true) {
    int sTileWidth=sWidth() / xTiles;
    int sTileHeight=sHeight() / yTiles;
    int subTileWidth=sTileWidth / sampleSize;
    int subTileHeight=sTileHeight / sampleSize;
    while (subTileWidth + xTiles + 1 > maxTileDimensions.x || (subTileWidth > getWidth() * 1.25 && sampleSize < fullImageSampleSize)) {
      xTiles+=1;
      sTileWidth=sWidth() / xTiles;
      subTileWidth=sTileWidth / sampleSize;
    }
    while (subTileHeight + yTiles + 1 > maxTileDimensions.y || (subTileHeight > getHeight() * 1.25 && sampleSize < fullImageSampleSize)) {
      yTiles+=1;
      sTileHeight=sHeight() / yTiles;
      subTileHeight=sTileHeight / sampleSize;
    }
    List<Tile> tileGrid=new ArrayList<>(xTiles * yTiles);
    for (int x=0; x < xTiles; x++) {
      for (int y=0; y < yTiles; y++) {
        Tile tile=new Tile();
        tile.sampleSize=sampleSize;
        tile.visible=sampleSize == fullImageSampleSize;
        tile.sRect=new Rect(x * sTileWidth,y * sTileHeight,x == xTiles - 1 ? sWidth() : (x + 1) * sTileWidth,y == yTiles - 1 ? sHeight() : (y + 1) * sTileHeight);
        tile.vRect=new Rect(0,0,0,0);
        tile.fileSRect=new Rect(tile.sRect);
        tileGrid.add(tile);
      }
    }
    tileMap.put(sampleSize,tileGrid);
    if (sampleSize == 1) {
      break;
    }
 else {
      sampleSize/=2;
    }
  }
}
