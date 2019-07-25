public double distance(SparseVector a,SparseVector b){
  double ret=a.dotProduct(b) / (a.twoNorm() * b.twoNorm());
  return 1.0 - ret;
}
