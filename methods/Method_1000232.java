private int bfs(List<List<Integer>> forest,int target,int sR,int sC){
  if (forest.get(sR).get(sC) == target) {
    forest.get(sR).set(sC,1);
    return 0;
  }
  Cell start=new Cell(sR,sC);
  start.distance=0;
  Queue<Cell> queue=new ArrayDeque<>();
  queue.add(start);
  boolean[][] done=new boolean[forest.size()][forest.get(0).size()];
  done[sR][sC]=true;
  while (!queue.isEmpty()) {
    Cell cell=queue.poll();
    for (int i=0; i < 4; i++) {
      int newR=cell.r + R[i];
      int newC=cell.c + C[i];
      Cell newCell=new Cell(newR,newC);
      if (newR >= 0 && newR < forest.size() && newC >= 0 && newC < forest.get(0).size() && forest.get(newR).get(newC) != 0 && !done[newCell.r][newCell.c]) {
        newCell.distance=cell.distance + 1;
        if (forest.get(newR).get(newC) == target) {
          forest.get(newR).set(newC,1);
          return newCell.distance;
        }
        done[newCell.r][newCell.c]=true;
        queue.offer(newCell);
      }
    }
  }
  return -1;
}
