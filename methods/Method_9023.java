public int getNextPageId(boolean forward){
  return positionToId.get(currentPosition + (forward ? 1 : -1),-1);
}
