public void calcAlpha(){
  alpha=0.0;
  for (  Path p : lpath) {
    alpha=logsumexp(alpha,p.cost + p.lnode.alpha,p == lpath.get(0));
  }
  alpha+=cost;
}
