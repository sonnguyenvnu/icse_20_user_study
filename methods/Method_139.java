int sink(char[][] grid,int i,int j){
  if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
    return 0;
  }
  grid[i][j]='0';
  sink(grid,i + 1,j);
  sink(grid,i - 1,j);
  sink(grid,i,j + 1);
  sink(grid,i,j - 1);
  return 1;
}
