/** 
 * Get the next tile to search for
 * @return -1 if not found
 */
private long next(){
  while (true) {
    final long index;
synchronized (mTileAreas) {
      if (!mTileIndices.hasNext()) {
        return -1;
      }
      index=mTileIndices.next();
    }
    final Drawable drawable=mCache.getMapTile(index);
    if (drawable == null) {
      return index;
    }
  }
}
