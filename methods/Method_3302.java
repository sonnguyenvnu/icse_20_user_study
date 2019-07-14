public double collins(List<Double> collins){
  if (x_.isEmpty()) {
    return 0.0;
  }
  buildLattice();
  viterbi();
  double s=0.0;
  int num=0;
  for (int i=0; i < x_.size(); i++) {
    if (answer_.get(i).equals(result_.get(i))) {
      num++;
    }
  }
  if (num == x_.size()) {
    return 0.0;
  }
  for (int i=0; i < x_.size(); i++) {
    s+=node_.get(i).get(answer_.get(i)).cost;
    List<Integer> fvector=node_.get(i).get(answer_.get(i)).fVector;
    for (int k=0; fvector.get(k) != -1; k++) {
      int idx=fvector.get(k) + answer_.get(i);
      collins.set(idx,collins.get(idx) + 1);
    }
    List<Path> lpath=node_.get(i).get(answer_.get(i)).lpath;
    for (    Path p : lpath) {
      if (p.lnode.y == answer_.get(p.lnode.x)) {
        for (int j=0; p.fvector.get(j) != -1; j++) {
          int idx=p.fvector.get(j) + p.lnode.y * ysize_ + p.rnode.y;
          collins.set(idx,collins.get(i) + 1);
        }
        s+=p.cost;
        break;
      }
    }
    s-=node_.get(i).get(result_.get(i)).cost;
    List<Integer> fvectorR=node_.get(i).get(result_.get(i)).fVector;
    for (int k=0; fvectorR.get(k) != -1; k++) {
      int idx=fvector.get(k) + result_.get(i);
      collins.set(idx,collins.get(idx) - 1);
    }
    List<Path> lpathR=node_.get(i).get(result_.get(i)).lpath;
    for (    Path p : lpathR) {
      if (p.lnode.y == result_.get(p.lnode.x)) {
        for (int j=0; p.fvector.get(j) != -1; j++) {
          int idx=p.fvector.get(j) + p.lnode.y * ysize_ + p.rnode.y;
          collins.set(idx,collins.get(i) - 1);
        }
        s-=p.cost;
        break;
      }
    }
  }
  return -s;
}
