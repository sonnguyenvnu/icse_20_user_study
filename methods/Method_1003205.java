@Override public boolean next(){
  int count=rows.size();
  if (rowId < count) {
    return ++rowId < count;
  }
  return false;
}
