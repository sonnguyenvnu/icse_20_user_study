private boolean contains(int pValue,final int pFirst,final int pSize){
  while (pValue < pFirst) {
    pValue+=mMapTileUpperBound;
  }
  return pValue < pFirst + pSize;
}
