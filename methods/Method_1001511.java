public boolean clean(Grid grid){
  System.err.println("CleanerMoveBlock");
  for (  Line line : grid.lines().toList()) {
    tryGrid(grid,line);
  }
  return false;
}
