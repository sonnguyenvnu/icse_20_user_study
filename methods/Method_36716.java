protected void onRemoved(){
  for (  BaseCell cell : mCells) {
    cell.removed();
  }
}
