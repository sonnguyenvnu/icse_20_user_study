@Override public boolean test(final LazyElementCell elementCell){
  final Cell cell=elementCell.getCell();
  final byte flag=getFlag(cell);
  final boolean isEdge=flag != HBaseStoreConstants.ENTITY;
  if (!edges && isEdge) {
    return false;
  }
  if (!entities && !isEdge) {
    return false;
  }
  return !isEdge || testEdge(flag,cell);
}
