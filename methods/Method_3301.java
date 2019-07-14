/** 
 * ????
 * @param expected ????
 * @return ??????
 */
public double gradient(double[] expected){
  if (x_.isEmpty()) {
    return 0.0;
  }
  buildLattice();
  forwardbackward();
  double s=0.0;
  for (int i=0; i < x_.size(); i++) {
    for (int j=0; j < ysize_; j++) {
      node_.get(i).get(j).calcExpectation(expected,Z_,ysize_);
    }
  }
  for (int i=0; i < x_.size(); i++) {
    List<Integer> fvector=node_.get(i).get(answer_.get(i)).fVector;
    for (int j=0; fvector.get(j) != -1; j++) {
      int idx=fvector.get(j) + answer_.get(i);
      expected[idx]--;
    }
    s+=node_.get(i).get(answer_.get(i)).cost;
    List<Path> lpath=node_.get(i).get(answer_.get(i)).lpath;
    for (    Path p : lpath) {
      if (p.lnode.y == answer_.get(p.lnode.x)) {
        for (int k=0; p.fvector.get(k) != -1; k++) {
          int idx=p.fvector.get(k) + p.lnode.y * ysize_ + p.rnode.y;
          expected[idx]--;
        }
        s+=p.cost;
        break;
      }
    }
  }
  viterbi();
  return Z_ - s;
}
