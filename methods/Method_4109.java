public Tile<T> removeAtPos(int startPosition){
  Tile<T> tile=mTiles.get(startPosition);
  if (mLastAccessedTile == tile) {
    mLastAccessedTile=null;
  }
  mTiles.delete(startPosition);
  return tile;
}
