@Override public boolean isUnidirected(Direction dir){
  if (unidirected)   return dir == Direction.OUT;
 else   return dir == Direction.BOTH;
}
