@Override public int getSourcePos(int i){
  return wrapped.getSourcePos(i) + offset;
}
