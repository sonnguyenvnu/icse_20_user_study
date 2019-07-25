@Override public boolean next(){
  if (rowId < rowCount) {
    rowId++;
    remapIfOld();
    if (rowId < rowCount) {
      if (rowId - rowOffset >= result.size()) {
        fetchRows(true);
      }
      currentRow=result.get(rowId - rowOffset);
      return true;
    }
    currentRow=null;
  }
  return false;
}
