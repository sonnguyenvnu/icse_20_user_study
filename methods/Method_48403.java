@Override public void movePositionTo(int newPosition){
  assert newPosition >= 0 && newPosition <= length();
  position=newPosition;
}
