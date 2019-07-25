@Override public Result evaluate(final Stats stats){
  return new Result(this,this.value <= stats.numDocs);
}
