private boolean mergeable(Grid grid,Line line1,Line line2){
  for (  Col col : grid.cols().toList()) {
    final Placeable cell1=grid.getCell(line1,col).getData();
    final Placeable cell2=grid.getCell(line2,col).getData();
    if (mergeable(cell1,cell2) == false) {
      return false;
    }
  }
  return true;
}
