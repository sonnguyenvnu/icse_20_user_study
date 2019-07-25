@Override public boolean contains(final long pTileIndex){
  if (mTileIndices == null) {
    return false;
  }
  for (int i=0; i < mSize; i++) {
    if (mTileIndices[i] == pTileIndex) {
      return true;
    }
  }
  return false;
}
