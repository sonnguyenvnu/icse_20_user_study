protected void addError(Node loc,String msg){
  Indexer.idx.putProblem(loc,msg);
}
