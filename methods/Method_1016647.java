@Override public SelectionCoreImpl all(){
  if (isDistinct) {
    throw new IllegalStateException("DISTINCT function can only be used with one column");
  }
  if (columns != null) {
    throw new IllegalStateException("Can't select all columns over columns selected previously");
  }
  if (currentSelection != null) {
    throw new IllegalStateException("Can't select all columns over columns selected previously");
  }
  return this;
}
