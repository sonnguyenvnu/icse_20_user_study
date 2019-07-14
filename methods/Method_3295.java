public void calcBeta(){
  beta=0.0;
  for (  Path p : rpath) {
    beta=logsumexp(beta,p.cost + p.rnode.beta,p == rpath.get(0));
  }
  beta+=cost;
}
