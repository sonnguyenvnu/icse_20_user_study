public boolean clean(Grid grid){
  boolean result=false;
  for (  Line line : grid.lines().toList()) {
    if (grid.usedColsOf(line).isEmpty()) {
      grid.removeLine(line);
      result=true;
    }
  }
  return result;
}
