protected void onAdded(){
  for (  BaseCell cell : mCells) {
    cell.added();
  }
}
