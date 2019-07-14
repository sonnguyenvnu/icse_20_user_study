@Override public int getLen(){
  assert !type.isUnidirected(Direction.IN);
  return type.isUnidirected(Direction.OUT) ? 1 : 2;
}
