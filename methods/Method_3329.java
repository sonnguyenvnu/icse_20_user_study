@Override public void tag(Table table){
  int size=table.size();
  if (size == 1) {
    table.setLast(0,"S");
    return;
  }
  double[][] net=new double[size][4];
  for (int i=0; i < size; ++i) {
    LinkedList<double[]> scoreList=computeScoreList(table,i);
    for (int tag=0; tag < 4; ++tag) {
      net[i][tag]=computeScore(scoreList,tag);
    }
  }
  net[0][idM]=-1000.0;
  net[0][idE]=-1000.0;
  int[][] from=new int[size][4];
  double[][] maxScoreAt=new double[2][4];
  System.arraycopy(net[0],0,maxScoreAt[0],0,4);
  int curI=0;
  for (int i=1; i < size; ++i) {
    curI=i & 1;
    int preI=1 - curI;
    for (int now=0; now < 4; ++now) {
      double maxScore=-1e10;
      for (int pre=0; pre < 4; ++pre) {
        double score=maxScoreAt[preI][pre] + matrix[pre][now] + net[i][now];
        if (score > maxScore) {
          maxScore=score;
          from[i][now]=pre;
          maxScoreAt[curI][now]=maxScore;
        }
      }
      net[i][now]=maxScore;
    }
  }
  int maxTag=maxScoreAt[curI][idS] > maxScoreAt[curI][idE] ? idS : idE;
  table.setLast(size - 1,id2tag[maxTag]);
  maxTag=from[size - 1][maxTag];
  for (int i=size - 2; i > 0; --i) {
    table.setLast(i,id2tag[maxTag]);
    maxTag=from[i][maxTag];
  }
  table.setLast(0,id2tag[maxTag]);
}
