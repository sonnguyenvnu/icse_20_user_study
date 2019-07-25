@Override public OperationChain execute(){
  return new OperationChain.Builder().first(getAllElements()).then(new Limit.Builder<Element>().resultLimit(1).build()).build();
}
