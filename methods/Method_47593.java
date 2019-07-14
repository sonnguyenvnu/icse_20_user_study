/** 
 * Returns mapping of any input degrees (0 to 360) to one of 60 selectable output degrees, where the degrees corresponding to visible numbers (i.e. those divisible by 30) will be weighted heavier than the degrees corresponding to non-visible numbers. See  {@link #preparePrefer30sMap()} documentation for the rationale and generation of themapping.
 */
private int snapPrefer30s(int degrees){
  if (mSnapPrefer30sMap == null) {
    return -1;
  }
  return mSnapPrefer30sMap[degrees];
}
