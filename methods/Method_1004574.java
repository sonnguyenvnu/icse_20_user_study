/** 
 * Returns true if the given tile source and tile coordinates exist in the cache
 * @since 5.6
 */
public boolean exists(final String pTileSource,final long pMapTileIndex){
  return 1 == getRowCount(primaryKey,getPrimaryKeyParameters(getIndex(pMapTileIndex),pTileSource));
}
