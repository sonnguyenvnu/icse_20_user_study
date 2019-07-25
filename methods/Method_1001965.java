public boolean equals(TextGrid grid){
  if (grid.getHeight() != this.getHeight() || grid.getWidth() != this.getWidth()) {
    return false;
  }
  int height=grid.getHeight();
  for (int i=0; i < height; i++) {
    String row1=this.getRow(i).toString();
    String row2=grid.getRow(i).toString();
    if (!row1.equals(row2))     return false;
  }
  return true;
}
