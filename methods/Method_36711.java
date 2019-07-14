public void removeAllCells(){
  for (int i=0, size=mCells.size(); i < size; i++) {
    mCells.get(i).onRemoved();
  }
  mCells.clear();
}
