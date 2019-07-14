/** 
 * By default the View automatically calculates the optimal tile size. Set this to override this, and force an upper limit to the dimensions of the generated tiles. Passing  {@link #TILE_SIZE_AUTO} will re-enable the default behaviour.
 * @param maxPixelsX Maximum tile width.
 * @param maxPixelsY Maximum tile height.
 */
public void setMaxTileSize(int maxPixelsX,int maxPixelsY){
  this.maxTileWidth=maxPixelsX;
  this.maxTileHeight=maxPixelsY;
}
