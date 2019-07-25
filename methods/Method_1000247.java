private void enqueue(Queue<State> queue,List<State> parents,int player,int col,int[][][] color,int[][][] degree){
  for (  State parent : parents) {
    if (color[parent.mPos][parent.catPos][parent.player] == DRAW) {
      if (parent.player == player) {
        color[parent.mPos][parent.catPos][parent.player]=col;
        queue.offer(new State(parent.mPos,parent.catPos,parent.player,col));
      }
 else {
        int currDegree=--degree[parent.mPos][parent.catPos][parent.player];
        if (currDegree == 0) {
          color[parent.mPos][parent.catPos][parent.player]=col;
          queue.offer(new State(parent.mPos,parent.catPos,parent.player,col));
        }
      }
    }
  }
}
