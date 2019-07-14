public int numNeighbors(int[][] grid,int x,int y){
  if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] == 0) {
    return 1;
  }
  if (grid[x][y] == -1) {
    return 0;
  }
  grid[x][y]=-1;
  return numNeighbors(grid,x + 1,y) + numNeighbors(grid,x - 1,y) + numNeighbors(grid,x,y + 1) + numNeighbors(grid,x,y - 1);
}
