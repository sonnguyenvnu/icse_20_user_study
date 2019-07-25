@Override public boolean contains(long pTileIndex){
  if (MapTileIndex.getZoom(pTileIndex) != mZoom) {
    return false;
  }
  if (!contains(MapTileIndex.getX(pTileIndex),mLeft,mWidth)) {
    return false;
  }
  return contains(MapTileIndex.getY(pTileIndex),mTop,mHeight);
}
