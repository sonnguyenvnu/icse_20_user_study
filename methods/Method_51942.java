@Override public boolean hasNext(){
  moveToNext();
  return i < parent.jjtGetNumChildren();
}
