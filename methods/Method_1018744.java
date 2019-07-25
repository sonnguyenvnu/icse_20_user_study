final void clear(){
  for (int i=0; i < rowMark.length; ++i) {
    rowMark[i]=null;
    updatedRow[i]=false;
    deletedRow[i]=false;
    rowType[i]=RowType.UNKNOWN;
  }
  assert size > 0;
  maxRows=size;
  reset();
}
