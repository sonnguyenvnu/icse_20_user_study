@Benchmark @Group("ring") @GroupThreads(1) public void loop(Link l){
  l.link();
}
