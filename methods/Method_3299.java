public void viterbi(){
  for (int i=0; i < x_.size(); i++) {
    for (int j=0; j < ysize_; j++) {
      double bestc=-1e37;
      Node best=null;
      List<Path> lpath=node_.get(i).get(j).lpath;
      for (      Path p : lpath) {
        double cost=p.lnode.bestCost + p.cost + node_.get(i).get(j).cost;
        if (cost > bestc) {
          bestc=cost;
          best=p.lnode;
        }
      }
      node_.get(i).get(j).prev=best;
      node_.get(i).get(j).bestCost=best != null ? bestc : node_.get(i).get(j).cost;
    }
  }
  double bestc=-1e37;
  Node best=null;
  int s=x_.size() - 1;
  for (int j=0; j < ysize_; j++) {
    if (bestc < node_.get(s).get(j).bestCost) {
      best=node_.get(s).get(j);
      bestc=node_.get(s).get(j).bestCost;
    }
  }
  for (Node n=best; n != null; n=n.prev) {
    result_.set(n.x,n.y);
  }
  cost_=-node_.get(x_.size() - 1).get(result_.get(x_.size() - 1)).bestCost;
}
