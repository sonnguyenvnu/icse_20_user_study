@Override public boolean accept(final Key key,final Value value){
  final byte flag=getFlag(key);
  final boolean isEdge=flag != ByteEntityPositions.ENTITY;
  if (!edges && isEdge) {
    return false;
  }
 else   if (!entities && !isEdge) {
    return false;
  }
  return !isEdge || checkEdge(flag,key);
}
