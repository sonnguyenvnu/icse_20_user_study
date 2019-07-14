@Override public GraphComputer result(ResultGraph resultGraph){
  Preconditions.checkNotNull(resultGraph,"Need to specify mode");
  this.resultGraphMode=resultGraph;
  return this;
}
