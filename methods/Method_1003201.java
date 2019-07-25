@Override public void reset(){
  if (closed) {
    throw DbException.throwInternalError();
  }
  rowId=-1;
  afterLast=false;
  currentRow=null;
  nextRow=null;
}
