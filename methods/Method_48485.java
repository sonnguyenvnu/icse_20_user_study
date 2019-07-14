private static DirectionID getDirID(Direction dir,RelationCategory rt){
switch (rt) {
case PROPERTY:
    assert dir == Direction.OUT;
  return DirectionID.PROPERTY_DIR;
case EDGE:
switch (dir) {
case OUT:
  return DirectionID.EDGE_OUT_DIR;
case IN:
return DirectionID.EDGE_IN_DIR;
default :
throw new IllegalArgumentException("Invalid direction: " + dir);
}
default :
throw new IllegalArgumentException("Invalid relation type: " + rt);
}
}
