public int maxKilledEnemies(char[][] grid){
  if (grid == null || grid.length == 0 || grid[0].length == 0) {
    return 0;
  }
  int max=0;
  int row=0;
  int[] col=new int[grid[0].length];
  for (int i=0; i < grid.length; i++) {
    for (int j=0; j < grid[0].length; j++) {
      if (grid[i][j] == 'W') {
        continue;
      }
      if (j == 0 || grid[i][j - 1] == 'W') {
        row=killedEnemiesRow(grid,i,j);
      }
      if (i == 0 || grid[i - 1][j] == 'W') {
        col[j]=killedEnemiesCol(grid,i,j);
      }
      if (grid[i][j] == '0') {
        max=(row + col[j] > max) ? row + col[j] : max;
      }
    }
  }
  return max;
}
