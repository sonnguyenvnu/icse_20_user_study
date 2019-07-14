public Tile<T> addOrReplace(Tile<T> newTile){
  final int index=mTiles.indexOfKey(newTile.mStartPosition);
  if (index < 0) {
    mTiles.put(newTile.mStartPosition,newTile);
    return null;
  }
  Tile<T> oldTile=mTiles.valueAt(index);
  mTiles.setValueAt(index,newTile);
  if (mLastAccessedTile == oldTile) {
    mLastAccessedTile=newTile;
  }
  return oldTile;
}
