@Override public Iterator<Long> iterator(){
  return new Iterator<Long>(){
    @Override public boolean hasNext(){
      return mIndex < size();
    }
    @Override public Long next(){
      if (!hasNext()) {
        return null;
      }
      int x=mLeft + mIndex % mWidth;
      int y=mTop + mIndex / mWidth;
      mIndex++;
      while (x >= mMapTileUpperBound) {
        x-=mMapTileUpperBound;
      }
      while (y >= mMapTileUpperBound) {
        y-=mMapTileUpperBound;
      }
      return MapTileIndex.getTileIndex(mZoom,x,y);
    }
    @Override public void remove(){
      throw new UnsupportedOperationException();
    }
  }
;
}
