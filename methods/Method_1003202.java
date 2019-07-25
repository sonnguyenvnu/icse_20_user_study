@Override public boolean next(){
  if (!closed && rowId < rowCount) {
    rowId++;
    if (rowId < rowCount) {
      if (external != null) {
        currentRow=external.next();
      }
 else {
        currentRow=rows.get(rowId);
      }
      return true;
    }
    currentRow=null;
  }
  return false;
}
