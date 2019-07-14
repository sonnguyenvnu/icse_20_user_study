/** 
 * By default the View automatically calculates the optimal tile size. Set this to override this, and force an upper limit to the dimensions of the generated tiles. Passing  {@link #TILE_SIZE_AUTO} will re-enable the default behaviour.
 * @param maxPixels Maximum tile size X and Y in pixels.
 */
public void setMaxTileSize(int maxPixels){
  this.maxTileWidth=maxPixels;
  this.maxTileHeight=maxPixels;
}
