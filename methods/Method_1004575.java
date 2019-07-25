/** 
 * Returns true if the given tile source and tile coordinates exist in the cache
 * @since 5.6
 */
@Override public boolean exists(final ITileSource pTileSource,final long pMapTileIndex){
  return exists(pTileSource.name(),pMapTileIndex);
}
